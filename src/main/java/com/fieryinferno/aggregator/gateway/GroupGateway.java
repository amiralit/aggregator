package com.fieryinferno.aggregator.gateway;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

/**
 * Created by atahmasebi on 4/23/16.
 */
public interface GroupGateway {
    Optional<JsonNode> getGroupStandings(final String groupId);
}
