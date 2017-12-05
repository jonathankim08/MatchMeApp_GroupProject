package com.example.jonat.matchmeapp_groupproject;

import android.media.Image;

/**
 * Created by neilsk on 12/5/17.
 */

public class ProfileClass {

    public String profileName, profileAge, profileLocation, profileTennisLevel, profileChessLevel;

    public ProfileClass() {
    }

    public ProfileClass(String profileName, String profileAge, String profileLocation, String profileTennisLevel, String profileChessLevel) {
        this.profileName = profileName;
        this.profileAge = profileAge;
        this.profileLocation = profileLocation;
        this.profileTennisLevel = profileTennisLevel;
        this.profileChessLevel = profileChessLevel;
    }
}
