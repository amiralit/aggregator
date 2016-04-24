package com.fieryinferno.aggregator;


import com.fieryinferno.aggregator.gateway.GroupGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    @Autowired
    private GroupGateway groupGateway;

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {

        //System.out.println(myName);
        System.out.println(groupGateway.getGroupInfo(1));
    }
}
