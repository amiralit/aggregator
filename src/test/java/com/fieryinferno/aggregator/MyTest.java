package com.fieryinferno.aggregator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fieryinferno.aggregator.models.Table;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atahmasebi on 4/23/16.
 */
public class MyTest {
    @Test
    public void test(){
        String jsonString = "{\"table\":[{\"id\":\"3776\",\"group\":\"1\",\"conference\":\"0\",\"team\":\"Chile\",\"points\":\"7\",\"wins\":2,\"draws\":1,\"losses\":0,\"gf\":\"10\",\"ga\":\"3\",\"avg\":7,\"mark\":1,\"round\":\"3\",\"pos\":\"1\",\"shield\":\"http:\\/\\/thumb.resfu.com\\/img_data\\/escudos\\/medium\\/3776.jpg?size=60x&ext=png&lossy=1&1\",\"form\":\"wdw\",\"direction\":\"\"},{\"id\":\"3769\",\"group\":\"1\",\"conference\":\"0\",\"team\":\"Bolivia\",\"points\":\"4\",\"wins\":1,\"draws\":1,\"losses\":1,\"gf\":\"3\",\"ga\":\"7\",\"avg\":-4,\"mark\":1,\"round\":\"3\",\"pos\":\"2\",\"shield\":\"http:\\/\\/thumb.resfu.com\\/img_data\\/escudos\\/medium\\/3769.jpg?size=60x&ext=png&lossy=1&1\",\"form\":\"dwl\",\"direction\":\"\"},{\"id\":\"3771\",\"group\":\"1\",\"conference\":\"0\",\"team\":\"Ecuador\",\"points\":\"3\",\"wins\":1,\"draws\":0,\"losses\":2,\"gf\":\"4\",\"ga\":\"6\",\"avg\":-2,\"mark\":2,\"round\":\"3\",\"pos\":\"3\",\"shield\":\"http:\\/\\/thumb.resfu.com\\/img_data\\/escudos\\/medium\\/3771.jpg?size=60x&ext=png&lossy=1&1\",\"form\":\"llw\",\"direction\":\"u\"},{\"id\":\"3811\",\"group\":\"1\",\"conference\":\"0\",\"team\":\"Mexico\",\"points\":\"2\",\"wins\":0,\"draws\":2,\"losses\":1,\"gf\":\"4\",\"ga\":\"5\",\"avg\":-1,\"mark\":\"\",\"round\":\"3\",\"pos\":\"4\",\"shield\":\"http:\\/\\/thumb.resfu.com\\/img_data\\/escudos\\/medium\\/3811.jpg?size=60x&ext=png&lossy=1&1\",\"form\":\"ddl\",\"direction\":\"d\"}]}";
        String standingStr = "{\"id\":\"3776\",\"group\":\"1\",\"conference\":\"0\",\"team\":\"Chile\",\"points\":\"7\",\"wins\":2,\"draws\":1,\"losses\":0,\"gf\":\"10\",\"ga\":\"3\",\"avg\":7,\"mark\":1,\"round\":\"3\",\"pos\":\"1\",\"shield\":\"http:\\/\\/thumb.resfu.com\\/img_data\\/escudos\\/medium\\/3776.jpg?size=60x&ext=png&lossy=1&1\",\"form\":\"wdw\",\"direction\":\"\"}";

        try {
            final Table table = new ObjectMapper().readValue(jsonString, Table.class);
            System.out.println(table.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
