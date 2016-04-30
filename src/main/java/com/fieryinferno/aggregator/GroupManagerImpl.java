package com.fieryinferno.aggregator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fieryinferno.aggregator.events.Event;
import com.fieryinferno.aggregator.events.EventTypes;
import com.fieryinferno.aggregator.events.Observer;
import com.fieryinferno.aggregator.events.Publisher;
import com.fieryinferno.aggregator.gateway.FirebaseGateway;
import com.fieryinferno.aggregator.gateway.GroupGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Created by atahmasebi on 4/26/16.
 */
public class GroupManagerImpl implements GroupManager, Observer{

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupManagerImpl.class);

    @Autowired
    private GroupGateway groupGateway;

    @Autowired
    private FirebaseGateway firebaseGateway;

    @Override
    public void updateGroupStandings() {
        updateGroupStandings("1");
        updateGroupStandings("2");
        updateGroupStandings("3");
        updateGroupStandings("4");
    }

    private void updateGroupStandings(final String groupId){
        final Optional<JsonNode> jsonNode = groupGateway.getGroupStandings(groupId);

        jsonNode.ifPresent(g -> firebaseGateway.putGroupStandings(groupId, g));

    }

    @Override
    public void notify(Event event) {
        if (event.getEventType() == EventTypes.MATCH_ENDED){
            LOGGER.info("match ended - {}", event.getMatchId());
            updateGroupStandings();
        }
    }
}
