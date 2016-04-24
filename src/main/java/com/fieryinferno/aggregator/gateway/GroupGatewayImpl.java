package com.fieryinferno.aggregator.gateway;

import com.fieryinferno.aggregator.gateway.commands.RestCommandFactory;
import com.fieryinferno.aggregator.models.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by atahmasebi on 4/23/16.
 */
@Component
public class GroupGatewayImpl implements GroupGateway{

    @Autowired
    private RestCommandFactory restCommandFactory;

    @Override
    public Table getGroupInfo(int groupId) {

        final String url = "http://www.resultados-futbol.com/scripts/api/api.php?key=40b2f1fd2a56cbd88df8b2c9b291760f&req=tables&format=json&lang=en&league=177&group={groupId}&year=2015";

        return restCommandFactory.<Table>getDefaultGetCommand(url, Collections.singletonMap("groupId", "2"), new ParameterizedTypeReference<Table>() {}).execute();
    }
}
