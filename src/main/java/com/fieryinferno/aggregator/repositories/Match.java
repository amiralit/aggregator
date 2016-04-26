package com.fieryinferno.aggregator.repositories;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

/**
 * Created by atahmasebi on 4/24/16.
 */
public class Match {

    public static enum MatchStatus{
        NOT_STARTED,
        IN_PROGRESS,
        ENDED
    }

    @Id
    private String id;

    private String matchId;

    private DateTime startDate;

    private MatchStatus matchStatus;

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", matchId='" + matchId + '\'' +
                ", startDate=" + startDate +
                ", matchStatus=" + matchStatus +
                '}';
    }
}
