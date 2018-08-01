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
import com.mationate.prueba3.models.Cat;
import com.squareup.picasso.Picasso;

public class CatAdapter extends FirebaseRecyclerAdapter<Cat, CatAdapter.CatHolder> {

    private CatListener listener;


    public CatAdapter(@NonNull FirebaseRecyclerOptions<Cat> options, CatListener listener) {
        super(options);
        this.listener = listener;
    }

    @NonNull
    @Override
    public CatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_cats, parent, false);
        return new CatHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final CatHolder holder, int position, @NonNull Cat model) {
        Picasso.get().load(model.getPhoto()).fit().centerCrop().into(holder.photo);
        holder.title.setText(model.getBreed());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cat auxCat = getItem(holder.getAdapterPosition());
                listener.clicked(auxCat);
            }
        });
    }


    public static class CatHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView title;

        public CatHolder(View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photoIv);
            title = itemView.findViewById(R.id.titleTv);
        }
    }

}
