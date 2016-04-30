package com.fieryinferno.aggregator.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by atahmasebi on 4/23/16.
 */
public class PublisherImpl implements Publisher {

    private final Map<EventTypes,Set<Observer>> observers;

    public PublisherImpl(){
        observers = new HashMap<>();
        Arrays.stream(EventTypes.values()).forEach(e -> {
            observers.put(e, new HashSet<>());
        });
    }

    @Override
    public void addObserver(EventTypes eventType, Observer observer) {
        observers.get(eventType).add(observer);
    }

    @Override
    public synchronized void publish(Event event) {
        observers.get(event.getEventType()).stream().forEach(o -> o.notify(event));
    }
}
