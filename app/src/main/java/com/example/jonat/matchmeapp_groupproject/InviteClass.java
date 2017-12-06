package com.example.jonat.matchmeapp_groupproject;

/**
 * Created by neilsk on 12/6/17.
 */

public class InviteClass {

    public String inviteUsername, inviteActivity, inviteTime, inviteDate, inviteStatus;

    public InviteClass() {
    }

    public InviteClass(String inviteUsername, String inviteActivity, String inviteTime, String inviteDate, String inviteStatus) {
        this.inviteUsername = inviteUsername;
        this.inviteActivity = inviteActivity;
        this.inviteTime = inviteTime;
        this.inviteDate = inviteDate;
        this.inviteStatus = inviteStatus;
    }
}