package com.example.jonat.matchmeapp_groupproject;

/**
 * Created by neilsk on 12/10/17.
 */

public class MatchPoolClass {
    public String matchPoolUserId, matchPoolActivity, matchPoolDay, matchPoolMonth, matchPoolSlot, matchPoolStatus;

    public String matchString;

    public MatchPoolClass() {
    }

    public MatchPoolClass(String matchPoolUserId, String matchPoolActivity, String matchPoolDay, String matchPoolMonth, String matchPoolSlot, String matchPoolStatus) {
        this.matchPoolUserId = matchPoolUserId;
        this.matchPoolActivity = matchPoolActivity;
        this.matchPoolDay = matchPoolDay;
        this.matchPoolMonth = matchPoolMonth;
        this.matchPoolSlot = matchPoolSlot;
        this.matchPoolStatus = matchPoolStatus;
        this.matchString = matchPoolActivity+matchPoolDay+matchPoolMonth+matchPoolSlot;
    }

    //methods to update the status = Open/Invited/Scheduled, default being Open
    public void UpdateStatustoInvited(String inviteUsername)
    {
        this.matchPoolStatus = "Invited";
    }
    public void UpdateStatustoScheduled(String inviteUsername)
    {
        this.matchPoolStatus = "Scheduled";
    }

}
