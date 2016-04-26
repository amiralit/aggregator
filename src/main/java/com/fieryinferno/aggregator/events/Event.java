package com.fieryinferno.aggregator.events;

/**
 * Created by atahmasebi on 4/23/16.
 */
public class Event {
    private EventTypes eventType;
    private String matchId;

    public Event(EventTypes eventType, String matchId) {
        this.eventType = eventType;
        this.matchId = matchId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public EventTypes getEventType() {
        return eventType;
    }

    public void setEventType(EventTypes eventType) {
        this.eventType = eventType;
    }
}
