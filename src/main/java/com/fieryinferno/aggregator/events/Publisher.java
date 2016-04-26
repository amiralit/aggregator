package com.fieryinferno.aggregator.events;

/**
 * Created by atahmasebi on 4/23/16.
 */
public interface Publisher {

    public void addObserver(EventTypes eventType, Observer observer);
    public void publish(Event event);
}
