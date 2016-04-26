package com.fieryinferno.aggregator.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fieryinferno.aggregator.gateway.FirebaseGateway;
import com.fieryinferno.aggregator.gateway.MatchGateway;
import com.fieryinferno.aggregator.repositories.Match;
import com.fieryinferno.aggregator.repositories.MatchRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by atahmasebi on 4/24/16.
 */
@Component
public class MatchServiceImpl implements MatchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchServiceImpl.class);

    @Autowired
    private MatchGateway matchGateway;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private FirebaseGateway firebaseGateway;

    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Optional<JsonNode> getMatchDetails(String matchId) {
        return matchGateway.getMatcheDetails(matchId);
    }

    @Override
    public List<Match> getCurrentMatches() {
        List<Match> matches = matchRepository.findByMatchStatus(Match.MatchStatus.NOT_STARTED);
        DateTime now = DateTime.now().withZone(DateTimeZone.forID("America/New_York"));
        return matches.stream().filter(match ->  match.getStartDate().isBefore(now)).collect(Collectors.toList());
    }

    @Override
    public void getMatches() {
        this.getMatches(1);
        this.getMatches(2);
        this.getMatches(3);
    }

    @Override
    public void uploadMatchDetails(String matchId, JsonNode matchDetails) {
        firebaseGateway.putMatchDetails(matchId, matchDetails);
    }

    @Override
    public Optional<Match> findMatchByMatchId(String matchId) {
        return Optional.ofNullable(matchRepository.findByMatchId(matchId));
    }

    @Override
    public void updateMatch(Match match) {
        matchRepository.save(match);
    }

    private void getMatches(final int round){
        final Optional<JsonNode> matches = matchGateway.getMatches(round);

        matches.ifPresent(jsonNode -> {
            for (JsonNode match : jsonNode.get("match")){
                final String matchId = match.get("id").asText();

                final Match mongoMatch = matchRepository.findByMatchId(matchId);

                if (mongoMatch == null){
                    final Match matchToInsert = new Match();
                    matchToInsert.setMatchId(matchId);
                    matchToInsert.setStartDate(DateTime.parse(match.get("schedule").asText(), FORMATTER));
                    matchToInsert.setMatchStatus(Match.MatchStatus.NOT_STARTED);
                    LOGGER.info("Inserting match: {}", matchToInsert);
                    matchRepository.insert(matchToInsert);

                    updateFirebase(matchId);
                }
            }
        });
    }

    private void updateFirebase(final String matchId){
        final Optional<JsonNode> matchDetails = matchGateway.getMatcheDetails(matchId);

        matchDetails.ifPresent(m -> firebaseGateway.putMatchDetails(matchId,m));
    }
}
