package com.fieryinferno.aggregator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fieryinferno.aggregator.models.Table;
import com.firebase.security.token.TokenGenerator;
import com.mongodb.Mongo;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by atahmasebi on 4/23/16.
 */
public class MyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyTest.class);

    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    @Test
    public void test(){
        Map<String, Object> authPayload = new HashMap<String, Object>();
        authPayload.put("uid", "1");

        TokenGenerator tokenGenerator = new TokenGenerator("hfj2AqzjV9M80kOHUK0FPgslMN5pevgsvSGnPN3J");
        String token = tokenGenerator.createToken(authPayload);

        LOGGER.info(token);
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
