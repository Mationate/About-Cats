package com.mationate.prueba3.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CurrentUser {

    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public String email()
    {
        return getCurrentUser().getEmail();
    }

    public String uid() {
        return currentUser.getUid();
    }

    public boolean isLogged() {
        if (currentUser != null) {
            return true;
        } else {
            return false;
        }

    }
}
