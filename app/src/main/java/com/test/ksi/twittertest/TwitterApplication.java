package com.test.ksi.twittertest;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.util.Base64;
import android.util.Log;

import com.twitter.sdk.android.core.Twitter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;


public class TwitterApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Twitter.initialize(this);

    }


}
