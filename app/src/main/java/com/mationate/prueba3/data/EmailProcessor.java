package com.mationate.prueba3.data;

public class EmailProcessor {

    public  String sanitizedEmail (String email){
        return email.replace("@","AT").replace(".","DOT");
    }

}
