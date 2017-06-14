package com.test.ksi.twittertest;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TwitterLoginButton loginButton;
    Button btnTwitterTest;
    TwitterAuthClient mTwitterAuthClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTwitterAuthClient = new TwitterAuthClient();

        btnTwitterTest = (Button) findViewById(R.id.btnTwitterTest);

        btnTwitterTest.setOnClickListener(this);

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls

                Log.d("loginSuccess", result.toString());

                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;

                Log.d("loginToken", token);
                Log.d("loginSecret", secret);

            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure

                Log.d("login", exception.toString());

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {

        boolean twitterInstalled = false;

        try {
            ApplicationInfo info = getPackageManager().
                    getApplicationInfo("com.twitter.android", 0);
            twitterInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        if (true) {
            mTwitterAuthClient.authorize(this, new com.twitter.sdk.android.core.Callback<TwitterSession>() {

                @Override
                public void success(Result<TwitterSession> twitterSessionResult) {
                    // Success

                    Log.d("loginSuccessCustom", twitterSessionResult.toString());

                    TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                    TwitterAuthToken authToken = session.getAuthToken();
                    String token = authToken.token;
                    String secret = authToken.secret;

                    Log.d("loginTokenCustom", token);
                    Log.d("loginSecretCustom", secret);

                }

                @Override
                public void failure(TwitterException e) {
                    e.printStackTrace();
                }
            });
        } else {
            Toast.makeText(this, "Not 0", Toast.LENGTH_LONG).show();
        }
    }
}
