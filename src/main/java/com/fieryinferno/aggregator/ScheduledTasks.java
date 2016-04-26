package com.fieryinferno.aggregator;


import com.fieryinferno.aggregator.gateway.GroupGateway;
import com.fieryinferno.aggregator.gateway.MatchGateway;
import com.fieryinferno.aggregator.repositories.Match;
import com.fieryinferno.aggregator.repositories.MatchRepository;
import com.fieryinferno.aggregator.services.LiveMatchUpdater;
import com.fieryinferno.aggregator.services.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private MatchManager matchManager;

    @Scheduled(fixedDelay = 60000)
    public void run() {
        matchManager.run();
    }
}
