package com.example.jonat.matchmeapp_groupproject;

public class MatchPoolClass {
    public String matchPoolUserId, matchPoolActivity, matchPoolDay, matchPoolMonth, matchPoolSlot, matchPoolStatus, matchString, matchString2, matchString3, matchDuplicateSlot, matchPoolProfileName, matchPoolProfileTennisLevel, matchPoolProfileChessLevel;
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
        this.matchString = matchPoolActivity + matchPoolDay + matchPoolMonth + matchPoolSlot;
        this.matchString2 = matchPoolActivity + matchPoolDay + matchPoolMonth + matchPoolProfileTennisLevel;
        this.matchString3 = matchPoolActivity + matchPoolDay + matchPoolMonth  + matchPoolProfileChessLevel;
        this.matchDuplicateSlot = matchPoolUserId + matchPoolActivity + matchPoolDay + matchPoolMonth + matchPoolSlot;
        this.matchPoolProfileName = matchPoolProfileName;
        this.matchPoolProfileTennisLevel = matchPoolProfileTennisLevel;
        this.matchPoolProfileChessLevel = matchPoolProfileChessLevel;
        this.matchPoolProfileLatitude = matchPoolProfileLatitude;
        this.matchPoolProfileLongitude = matchPoolProfileLongitude;
    }
}
