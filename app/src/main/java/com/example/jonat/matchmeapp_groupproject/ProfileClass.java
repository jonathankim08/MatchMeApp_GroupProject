package com.example.jonat.matchmeapp_groupproject;

public class ProfileClass {

    public String profileUserId, profileEmailAddress, profileName, profileAge, profileLocation, profileTennisLevel, profileChessLevel;
    public double profileLatitude, profileLongitude;

    public ProfileClass() {
    }

    public ProfileClass(String profileUserId ,String emailAddress, String profileName, String profileAge, String profileLocation, String profileTennisLevel, String profileChessLevel, double profileLatitude, double profileLongitude) {
        this.profileUserId = profileUserId;
        this.profileEmailAddress = emailAddress;
        this.profileName = profileName;
        this.profileAge = profileAge;
        this.profileLocation = profileLocation;
        this.profileTennisLevel = profileTennisLevel;
        this.profileChessLevel = profileChessLevel;
        this.profileLatitude = profileLatitude;
        this.profileLongitude = profileLongitude;
    }
}
