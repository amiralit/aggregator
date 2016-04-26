package com.fieryinferno.aggregator.events;

/**
 * Created by atahmasebi on 4/23/16.
 */
public interface Observer {
    public void notify(Event event);
}
