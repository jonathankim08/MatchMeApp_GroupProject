package com.example.jonat.matchmeapp_groupproject;

public class InviteClass {

    public String inviteUsername, inviteReceiver, inviteActivity, inviteSlot, inviteDay, inviteMonth, inviteStatus;

    public InviteClass() {
    }

    public InviteClass(String inviteUsername, String inviteReceiver, String inviteActivity, String inviteSlot, String inviteDay, String inviteMonth,  String inviteStatus) {
        this.inviteUsername = inviteUsername;
        this.inviteReceiver = inviteReceiver;
        this.inviteActivity = inviteActivity;
        this.inviteSlot = inviteSlot;
        this.inviteDay = inviteDay;
        this.inviteMonth = inviteMonth;
        this.inviteStatus = inviteStatus;
    }
}