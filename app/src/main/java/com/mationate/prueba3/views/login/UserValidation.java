package com.mationate.prueba3.views.login;

import com.mationate.prueba3.data.CurrentUser;

public class UserValidation {

    private LoginCallback callback;

    public UserValidation(LoginCallback callback) {
        this.callback = callback;
    }

    public void userValidation(){
        if (new CurrentUser().isLogged()){
            callback.logged();
        } else {
            callback.signUp();
        }

    }
}
