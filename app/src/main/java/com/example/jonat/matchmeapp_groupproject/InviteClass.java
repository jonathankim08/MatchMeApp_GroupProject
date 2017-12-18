package com.example.jonat.matchmeapp_groupproject;

public class InviteClass {

    public String inviteSender, inviteReceiver, inviteSenderName, inviteReceiverName, inviteActivity, inviteSlot, inviteDay, inviteMonth, inviteStatus, inviteSenderReceiverCheck ;
    public double inviteSenderLatitude, inviteSenderLongitude, inviteReceiverLatitude, inviteReceiverLongitude;

    public InviteClass() {
    }

    public InviteClass(String inviteSender, String inviteReceiver, String inviteSenderName, String inviteReceiverName, String inviteActivity, String inviteSlot, String inviteDay, String inviteMonth,  String inviteStatus, double inviteSenderLatitude, double inviteSenderLongitude, double inviteReceiverLatitude, double inviteReceiverLongitude) {
        this.inviteSender = inviteSender;
        this.inviteReceiver = inviteReceiver;
        this.inviteSenderName = inviteSenderName;
        this.inviteReceiverName = inviteReceiverName;
        this.inviteActivity = inviteActivity;
        this.inviteSlot = inviteSlot;
        this.inviteDay = inviteDay;
        this.inviteMonth = inviteMonth;
        this.inviteStatus = inviteStatus;
        this.inviteSenderLatitude = inviteSenderLatitude;
        this.inviteSenderLongitude = inviteSenderLongitude;
        this.inviteReceiverLatitude = inviteReceiverLatitude;
        this.inviteReceiverLongitude = inviteReceiverLongitude;
        this.inviteSenderReceiverCheck = inviteSender + inviteReceiver + inviteActivity + inviteDay + inviteMonth + inviteSlot;
    }
}