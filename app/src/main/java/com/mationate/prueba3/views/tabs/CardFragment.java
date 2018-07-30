package com.mationate.prueba3.views.tabs;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mationate.prueba3.R;
import com.mationate.prueba3.adapters.CatAdapter;
import com.mationate.prueba3.listener.CatListener;
import com.mationate.prueba3.data.Nodes;
import com.mationate.prueba3.models.Cat;
import com.squareup.picasso.Picasso;


public class CardFragment extends Fragment implements CatListener {

    public static final String CAT = "com.mationate.prueba3.views.tabs.KEY.CAT";


    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance (){
        return new CardFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rv.setLayoutManager(gridLayoutManager);
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        FirebaseRecyclerOptions<Cat> options = new FirebaseRecyclerOptions.Builder<Cat>()
                .setQuery(new Nodes().cats(),Cat.class)
                .setLifecycleOwner(getActivity())
                .build();

        CatAdapter adapter = new CatAdapter(options, this);
        rv.setAdapter(adapter);

        /*Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thing);


        dialog.dismiss();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();*/



    }

    @Override
    public void clicked(Cat cat) {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cat);

        TextView titleDialogTv = dialog.findViewById(R.id.titleDialogCat);
        titleDialogTv.setText(cat.getBreed());

        ImageView photoDialogIv = dialog.findViewById(R.id.photoDialogIv);
        Picasso.get().load(cat.getPhoto()).centerCrop().fit().into(photoDialogIv);

        TextView descriptionDialogTv = dialog.findViewById(R.id.descriptionDialogTv);
        descriptionDialogTv.setText(cat.getDescription());

        dialog.findViewById(R.id.dismissBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.favoriteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();

    }
}
