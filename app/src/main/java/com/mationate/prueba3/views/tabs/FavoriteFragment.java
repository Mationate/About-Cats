package com.mationate.prueba3.views.tabs;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mationate.prueba3.R;
import com.mationate.prueba3.adapters.FavoriteAdapter;
import com.mationate.prueba3.adapters.FavoriteListener;
import com.mationate.prueba3.data.CurrentUser;
import com.mationate.prueba3.data.Nodes;
import com.mationate.prueba3.models.Favorite;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteListener{


    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.favoriteRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);

        /*GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        rv.setLayoutManager(gridLayoutManager);*/

        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        FirebaseRecyclerOptions<Favorite> options = new FirebaseRecyclerOptions.Builder<Favorite>()
                .setQuery(new Nodes().userFavorite(new CurrentUser().uid()), Favorite.class)
                .setLifecycleOwner(getActivity())
                .build();

        FavoriteAdapter adapter = new FavoriteAdapter(options, (FavoriteListener) this);
        rv.setAdapter(adapter);
    }

    @Override
    public void delete(Favorite favorite) {
        new Nodes().userFavorite(new CurrentUser().uid()).child(favorite.getKey()).removeValue();
    }
}
