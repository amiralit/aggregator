package com.fieryinferno.aggregator.events;

import org.junit.Test;

/**
 * Created by atahmasebi on 4/23/16.
 */
public class PublisherImplTest {
    public static class ObserverImpl implements Observer{

        final String name;

        public ObserverImpl(String name) {
            this.name = name;
        }

        @Override
        public void notify(Event event) {
            System.out.println(name + " observed " + event.getEventType());
        }
    }


    @Test
    public void test(){
//        final ObserverImpl observer1 = new ObserverImpl("observer1");
//        final ObserverImpl observer2 = new ObserverImpl("observer2");
//
//        final Publisher publisher = new PublisherImpl();
//
//        publisher.addObserver(EventTypes.MATCH_STARTED, observer1);
//        publisher.addObserver(EventTypes.MATCH_ENDED, observer1);
//        publisher.addObserver(EventTypes.MATCH_ENDED, observer2);
//
//        final Event matchStartedEvent = new Event();
//        matchStartedEvent.setEventType(EventTypes.MATCH_STARTED);
//
//        final Event matchEndedEvent = new Event();
//        matchEndedEvent.setEventType(EventTypes.MATCH_ENDED);
//
//        publisher.publish(matchStartedEvent);
//        publisher.publish(matchEndedEvent);
    }
}
