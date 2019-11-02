package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GenresRecyclerAdapter extends RecyclerView.Adapter<GenresRecyclerAdapter.GenresViewHolder> {

    private List<Genre> genreList;
    private GenreAdapterListener genreAdapterListener;

    public GenresRecyclerAdapter(GenreAdapterListener genreAdapterListener) {
        this.genreList = new ArrayList<>();
        this.genreAdapterListener = genreAdapterListener;
    }

    @NonNull
    @Override
    public GenresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenresViewHolder(
            LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.genre_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenresViewHolder holder, int position) {
        holder.bindGenre(genreList.get(position));
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class GenresViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgGenrePicture;
        private TextView txtGenreName;

        public GenresViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgGenrePicture = itemView.findViewById(R.id.img_genre_picture);
            txtGenreName = itemView.findViewById(R.id.txt_genre_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    genreAdapterListener.getGenreFromAdapter(genreList, getAdapterPosition());
                }
            });
        }

        private void bindGenre(Genre genre) {
            Glide.with(itemView)
                    .load(genre.getPicture())
                    .placeholder(R.drawable.img_genre_placeholder)
                    .into(imgGenrePicture);
            txtGenreName.setText(genre.getName());
        }
    }

    public interface GenreAdapterListener {
        void getGenreFromAdapter(List<Genre> genres, Integer pos);
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

}