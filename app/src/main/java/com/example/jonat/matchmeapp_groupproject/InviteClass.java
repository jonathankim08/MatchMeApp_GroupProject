package com.example.jonat.matchmeapp_groupproject;

/**
 * Created by neilsk on 12/6/17.
 */

public class InviteClass {

    public String inviteUsername, inviteActivity, inviteSlot, inviteDay, inviteMonth, inviteStatus;

    public InviteClass() {
    }

    public InviteClass(String inviteUsername, String inviteActivity, String inviteDay, String inviteMonth, String inviteSlot, String inviteStatus) {
        this.inviteUsername = inviteUsername;
        this.inviteActivity = inviteActivity;
        this.inviteSlot = inviteSlot;
        this.inviteDay = inviteDay;
        this.inviteMonth = inviteMonth;
        this.inviteStatus = inviteStatus;
    }

    //methods to update the status = Open/Invited/Scheduled, default being Open
    public void UpdateStatustoInvited(String inviteUsername)
    {
        this.inviteStatus = "Invited";
    }
    public void UpdateStatustoScheduled(String inviteUsername)
    {
        this.inviteStatus = "Scheduled";
    }
}