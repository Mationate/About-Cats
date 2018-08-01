package com.mationate.prueba3.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Nodes {

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();

    public DatabaseReference cats (){
        return root.child("cats");
    }

    public DatabaseReference users (){
        return root.child("users");
    }

    public DatabaseReference user (String key){
        return users().child(key);
    }

    public DatabaseReference favorites (){
        return root.child("favorites");
    }

    public DatabaseReference userFavorite (String uid){
        return favorites().child(uid);
    }

}
