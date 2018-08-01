package com.mationate.prueba3.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mationate.prueba3.R;
import com.mationate.prueba3.models.Favorite;
import com.squareup.picasso.Picasso;

public class FavoriteAdapter extends FirebaseRecyclerAdapter<Favorite, FavoriteAdapter.FavoriteHolder> {

    private FavoriteListener listener;


    public FavoriteAdapter(@NonNull FirebaseRecyclerOptions<Favorite> options, FavoriteListener listener) {
        super(options);
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_favorites, parent, false);
        return new FavoriteHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FavoriteHolder holder, int position, @NonNull Favorite model) {

        Picasso.get().load(model.getPhoto()).fit().centerCrop().into(holder.favoritePhoto);
        holder.favoriteTitle.setText(model.getBreed());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Favorite auxFavorite = getItem(holder.getAdapterPosition());
                listener.delete(auxFavorite);
            }
        });

    }


    public static class FavoriteHolder extends RecyclerView.ViewHolder {

        private TextView favoriteTitle;
        private ImageView favoritePhoto;

        public FavoriteHolder(View itemView) {
            super(itemView);
            favoriteTitle = itemView.findViewById(R.id.favoriteTitleTv);
            favoritePhoto = itemView.findViewById(R.id.favoritePhotoIv);
        }
    }
}
