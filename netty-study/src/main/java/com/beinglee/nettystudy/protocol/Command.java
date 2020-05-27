package com.beinglee.nettystudy.protocol;

public abstract class Command {

    public static final byte LOGIN_REQUEST = 1;

    public static final byte LOGIN_RESPONSE = 2;

    public static final byte MSG_REQUEST = 3;

    public static final byte MSG_RESPONSE = 4;

    public static final byte LOGOUT_REQUEST = 5;

    public static final byte LOGOUT_RESPONSE = 6;

    public static final byte CREATE_GROUP_REQUEST = 7;

    public static final byte CREATE_GROUP_RESPONSE = 8;

}
