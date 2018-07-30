package com.mationate.prueba3.views.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.mationate.prueba3.R;
import com.mationate.prueba3.data.CurrentUser;
import com.mationate.prueba3.data.EmailProcessor;
import com.mationate.prueba3.data.Nodes;
import com.mationate.prueba3.models.LocalUser;
import com.mationate.prueba3.views.tabs.TabbedActivity;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginCallback {

    public static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         new UserValidation(this).userValidation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RC_SIGN_IN == requestCode) {
            if (RESULT_OK == resultCode) {
                logged();
            } else {
                signUp();
            }
        }
    }

    @Override
    public void logged() {
        Intent intent = new Intent(this, TabbedActivity.class);
        startActivity(intent);
        CurrentUser currentUser = new CurrentUser();
        LocalUser user = new LocalUser();
        user.setMail(currentUser.email());
        user.setName(currentUser.getCurrentUser().getDisplayName());
        user.setUid(currentUser.uid());

        String key = new EmailProcessor().sanitizedEmail(currentUser.email());
        new Nodes().user(key).setValue(user);
        finish();



    }

    @Override
    public void signUp() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.FacebookBuilder().build()/*.
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.PhoneBuilder().build()
                                new AuthUI.IdpConfig.TwitterBuilder().build()*/))
                        .setTheme(R.style.LoginTheme)
                        .setLogo(R.mipmap.catlogo)
                        .build(),
                RC_SIGN_IN);
    }
}



