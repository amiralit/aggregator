package com.fieryinferno.aggregator.gateway;

import com.fieryinferno.aggregator.models.Table;

/**
 * Created by atahmasebi on 4/23/16.
 */
public interface GroupGateway {
    Table getGroupInfo(final int groupId);
}
