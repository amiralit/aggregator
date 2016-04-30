package com.fieryinferno.aggregator.repositories;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
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

    private LocalDateTime startDate;

    private MatchStatus matchStatus;

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
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
