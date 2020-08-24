package coachingmateanalytics.coachingmate.entity;

import java.nio.Buffer;
import java.util.Date;

public class Coach {
    String user_id;
    String user_login;
    String user_pass;
    String user_activation_key;
    String user_email;
    String user_status;
    String display_name;
    Date created_date;
    String[] athlete_ids;
    String title;
    String first_name;
    String last_name;
    Date date_of_birth;
    String email;
    String mobile_phone;
    String biography;
    String[] planner_ids;
    String[] group_ids;
    Date registered_date;
    Buffer avatar;

    public Coach(String user_id, String user_login, String user_pass, String user_activation_key, String user_email, String user_status, String display_name, Date created_date) {
        this.user_id = user_id;
        this.user_login = user_login;
        this.user_pass = user_pass;
        this.user_activation_key = user_activation_key;
        this.user_email = user_email;
        this.user_status = user_status;
        this.display_name = display_name;
        this.created_date = created_date;
    }

    public Buffer getAvatar() {
        return avatar;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_activation_key() {
        return user_activation_key;
    }

    public void setUser_activation_key(String user_activation_key) {
        this.user_activation_key = user_activation_key;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String[] getAthlete_ids() {
        return athlete_ids;
    }

    public void setAthlete_ids(String[] athlete_ids) {
        this.athlete_ids = athlete_ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String[] getPlanner_ids() {
        return planner_ids;
    }

    public void setPlanner_ids(String[] planner_ids) {
        this.planner_ids = planner_ids;
    }

    public String[] getGroup_ids() {
        return group_ids;
    }

    public void setGroup_ids(String[] group_ids) {
        this.group_ids = group_ids;
    }

    public Date getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(Date registered_date) {
        this.registered_date = registered_date;
    }

    public void setAvatar(Buffer avatar) {
        this.avatar = avatar;
    }
}
