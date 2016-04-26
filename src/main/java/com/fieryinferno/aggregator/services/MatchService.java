package com.fieryinferno.aggregator.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fieryinferno.aggregator.repositories.Match;

import java.util.List;
import java.util.Optional;

/**
 * Created by atahmasebi on 4/24/16.
 */
public interface MatchService {
    Optional<JsonNode> getMatchDetails(String matchId);
    List<Match> getCurrentMatches();
    void getMatches();
    void uploadMatchDetails(final String matchId, final JsonNode matchDetails);
    Optional<Match> findMatchByMatchId(final String matchId);
    void updateMatch(final Match match);
}
