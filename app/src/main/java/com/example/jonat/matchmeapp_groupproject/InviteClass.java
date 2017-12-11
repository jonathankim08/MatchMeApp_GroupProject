package com.example.jonat.matchmeapp_groupproject;

import java.lang.reflect.Array;

/**
 * Created by neilsk on 12/6/17.
 */

public class InviteClass {

    public String inviteUsername, inviteReceiver, inviteActivity, inviteSlot, inviteDay, inviteMonth, inviteStatus;

    public InviteClass() {
    }

    public InviteClass(String inviteUsername, String inviteReceiver, String inviteActivity, String inviteDay, String inviteMonth, String inviteSlot, String inviteStatus) {
        this.inviteUsername = inviteUsername;
        this.inviteReceiver = inviteReceiver;
        this.inviteActivity = inviteActivity;
        this.inviteSlot = inviteSlot;
        this.inviteDay = inviteDay;
        this.inviteMonth = inviteMonth;
        this.inviteStatus = inviteStatus;
    }
}