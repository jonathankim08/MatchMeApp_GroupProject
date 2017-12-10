package com.example.jonat.matchmeapp_groupproject;

/**
 * Created by neilsk on 12/10/17.
 */

public class MatchPoolClass {
    public String matchPoolUsername, matchPoolActivity, matchPoolSlot, matchPoolDay, matchPoolMonth, matchPoolStatus;

    public MatchPoolClass() {
    }

    public MatchPoolClass(String matchPoolUsername, String matchPoolActivity, String matchPoolSlot, String matchPoolDay, String matchPoolMonth, String matchPoolStatus) {
        this.matchPoolUsername = matchPoolUsername;
        this.matchPoolActivity = matchPoolActivity;
        this.matchPoolSlot = matchPoolSlot;
        this.matchPoolDay = matchPoolDay;
        this.matchPoolMonth = matchPoolMonth;
        this.matchPoolStatus = matchPoolStatus;
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
