package com.fieryinferno.aggregator.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by atahmasebi on 4/24/16.
 */
public interface MatchRepository extends MongoRepository<Match, String> {
    Match findByMatchId(String matchId);
    List<Match> findByMatchStatus(Match.MatchStatus matchStatus);
}
