package com.fieryinferno.aggregator.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fieryinferno.aggregator.events.Event;
import com.fieryinferno.aggregator.events.EventTypes;
import com.fieryinferno.aggregator.events.Publisher;
import com.fieryinferno.aggregator.gateway.commands.RestGetCommand;
import com.fieryinferno.aggregator.repositories.Match;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by atahmasebi on 4/25/16.
 */
public class LiveMatchUpdater extends HystrixCommand<Void>{

    private static final Logger LOGGER = LoggerFactory.getLogger(LiveMatchUpdater.class);

    final private MatchService matchService;
    final Match match;
    final long pullRate;
    final Publisher publisher;

    public LiveMatchUpdater(final MatchService matchService, final Publisher publisher, final Match match, final long pullRate) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(RestGetCommand.class.getSimpleName())));

        this.matchService = matchService;
        this.match = match;
        this.pullRate = pullRate;
        this.publisher = publisher;
    }

    @Override
    protected Void run() throws Exception {

        new Timer().scheduleAtFixedRate(new TimerTask() {

            int i = 0;
            @Override
            public void run() {
                JsonNode matchDetails = getMatchDetails(match.getMatchId());
                Integer status = matchDetails.get("status").asInt();

                matchService.uploadMatchDetails(match.getMatchId(), matchDetails);
                LOGGER.info(matchDetails.toString());
                LOGGER.info("-------------------------------------");

                if (matchDetails.get("status").asInt() == 1 && LocalDateTime.now().isAfter(match.getStartDate().plusMinutes(230))){
                    endMatch();
                    this.cancel();
                }
            }
        }, 0, pullRate);

        return null;
    }


    @Override
    protected Void getFallback(){

        this.endMatch();
        return null;
    }

    private void endMatch(){

        this.publisher.publish(new Event(EventTypes.MATCH_ENDED, this.match.getMatchId()));

        final Optional<Match> match = matchService.findMatchByMatchId(this.match.getMatchId());
        match.ifPresent(m->{
            m.setMatchStatus(Match.MatchStatus.ENDED);
            matchService.updateMatch(m);
        });
    }

    private JsonNode getMatchDetails(final String matchId){
        final Optional<JsonNode> matcheDetails = matchService.getMatchDetails(matchId);

        if (matcheDetails.isPresent()){
            return matcheDetails.get();
        } else {
            throw new RuntimeException("getMatchDetails failed for matchId="+matchId);
        }
    }
}
