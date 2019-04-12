package com.zkzz.module.http.model;

public class ScoreModel {

    /**
     * us_id : 18
     * item : service_daskhjdkash
     * score : 80
     * granted : user_219
     * description : 这是机器人的评价
     * addition_info :
     * add_date : 2019-04-04T10:36:30.050771+08:00
     */

    private int us_id;
    private String item;
    private int score;
    private String granted;
    private String description;
    private String addition_info;
    private String add_date;

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGranted() {
        return granted;
    }

    public void setGranted(String granted) {
        this.granted = granted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddition_info() {
        return addition_info;
    }

    public void setAddition_info(String addition_info) {
        this.addition_info = addition_info;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    @Override
    public String toString() {
        return "ScoreModel{" +
                "us_id=" + us_id +
                ", item='" + item + '\'' +
                ", score=" + score +
                ", granted='" + granted + '\'' +
                ", description='" + description + '\'' +
                ", addition_info='" + addition_info + '\'' +
                ", add_date='" + add_date + '\'' +
                '}';
    }
}
