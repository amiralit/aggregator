package com.fieryinferno.aggregator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by atahmasebi on 4/23/16.
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class Standing{
    private String id;
    private String group;
    private String conference;
    private String team;
    private String points;
    private String wins;
    private String draws;
    private String losses;
    private String gf;
    private String ga;
    private String avg;
    private String mark;
    private String round;
    private String pos;
    private String shield;
    private String form;
    private String direction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getGf() {
        return gf;
    }

    public void setGf(String gf) {
        this.gf = gf;
    }

    public String getGa() {
        return ga;
    }

    public void setGa(String ga) {
        this.ga = ga;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getShield() {
        return shield;
    }

    public void setShield(String shield) {
        this.shield = shield;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Standing{" +
                "id='" + id + '\'' +
                ", group='" + group + '\'' +
                ", conference='" + conference + '\'' +
                ", team='" + team + '\'' +
                ", points='" + points + '\'' +
                ", wins='" + wins + '\'' +
                ", draws='" + draws + '\'' +
                ", losses='" + losses + '\'' +
                ", gf='" + gf + '\'' +
                ", ga='" + ga + '\'' +
                ", avg='" + avg + '\'' +
                ", mark='" + mark + '\'' +
                ", round='" + round + '\'' +
                ", pos='" + pos + '\'' +
                ", shield='" + shield + '\'' +
                ", form='" + form + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
