package com.fieryinferno.aggregator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fieryinferno.aggregator.events.Event;
import com.fieryinferno.aggregator.events.EventTypes;
import com.fieryinferno.aggregator.events.Observer;
import com.fieryinferno.aggregator.events.Publisher;
import com.fieryinferno.aggregator.repositories.Match;
import com.fieryinferno.aggregator.services.LiveMatchUpdater;
import com.fieryinferno.aggregator.services.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * Created by atahmasebi on 4/25/16.
 */
public class MatchManagerImpl implements MatchManager, Observer{

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchManagerImpl.class);

    @Autowired
    private Publisher publisher;

    @Autowired
    private MatchService matchService;

    @Override
    public void run() {
        final List<Match> currentMatches = matchService.getCurrentMatches();

        LOGGER.info("currentMatches={}", currentMatches);

        currentMatches.stream().forEach(currentMatch -> {
            final Optional<JsonNode> matchDetails = matchService.getMatchDetails(currentMatch.getMatchId());

            matchDetails.ifPresent(m -> {
                currentMatch.setMatchStatus(Match.MatchStatus.IN_PROGRESS);
                matchService.updateMatch(currentMatch);

                new LiveMatchUpdater(matchService, publisher, currentMatch.getMatchId(), 60000).queue();

                publisher.publish(new Event(EventTypes.MATCH_STARTED, currentMatch.getMatchId()));
            });
        });
    }

    @Override
    public void notify(Event event) {
//        if (event.getEventType() == EventTypes.MATCH_ENDED){
//            matchService.getMatches();
//        }
    }
}
