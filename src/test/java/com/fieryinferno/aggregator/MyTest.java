package com.fieryinferno.aggregator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fieryinferno.aggregator.models.Table;
import com.mongodb.Mongo;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atahmasebi on 4/23/16.
 */
public class MyTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    @Test
    public void test(){
        DateTime dateTime = DateTime.parse("2016-04-25 5:00:00", FORMATTER);
        DateTime now = DateTime.now().withZone(DateTimeZone.forID("Europe/Madrid"));

        if (dateTime.isAfter(now)){
            System.out.println("after now");
        } else {
            System.out.println("before now");
        }
    }

    @Test
    public void test1() {

        final LocalDateTime now = LocalDateTime.parse("2016-04-26 20:45:00", FORMATTER);
        final DateTime dateTime = new DateTime(now.toString(), DateTimeZone.forID("Europe/Madrid"));
        final DateTime dateTime1 =  new DateTime(dateTime).withZone(DateTimeZone.forID("America/Los_Angeles"));

        System.out.println(dateTime1.toLocalDateTime());
    }

    @Test
    public void test2(){
        System.out.println(DateTimeZone.getAvailableIDs());
    }


}
