package com.example.jonat.matchmeapp_groupproject;

/**
 * Created by neilsk on 12/10/17.
 */

public class MatchPoolClass {
    public String matchPoolUserId, matchPoolActivity, matchPoolDay, matchPoolMonth, matchPoolSlot, matchPoolStatus;
    public String matchString, matchDuplicateSlot;

    public String matchPoolProfileName, matchPoolProfileTennisLevel, matchPoolProfileChessLevel;
    public double matchPoolProfileLatitude, matchPoolProfileLongitude;

    public MatchPoolClass() {
    }

    public MatchPoolClass(String matchPoolUserId, String matchPoolActivity, String matchPoolDay, String matchPoolMonth, String matchPoolSlot, String matchPoolStatus, String matchPoolProfileName, String matchPoolProfileChessLevel, String matchPoolProfileTennisLevel, double matchPoolProfileLatitude, double matchPoolProfileLongitude) {
        this.matchPoolUserId = matchPoolUserId;
        this.matchPoolActivity = matchPoolActivity;
        this.matchPoolDay = matchPoolDay;
        this.matchPoolMonth = matchPoolMonth;
        this.matchPoolSlot = matchPoolSlot;
        this.matchPoolStatus = matchPoolStatus;
        this.matchString = matchPoolActivity+matchPoolDay+matchPoolMonth+matchPoolSlot;
        this.matchDuplicateSlot = matchPoolUserId + matchPoolActivity+matchPoolDay+matchPoolMonth+matchPoolSlot;
        this.matchPoolProfileName = matchPoolProfileName;
        this.matchPoolProfileTennisLevel = matchPoolProfileTennisLevel;
        this.matchPoolProfileChessLevel = matchPoolProfileChessLevel;
        this.matchPoolProfileLatitude = matchPoolProfileLatitude;
        this.matchPoolProfileLongitude = matchPoolProfileLongitude;
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
