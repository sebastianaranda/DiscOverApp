package com.arandasebastian.discoverapp.view;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.arandasebastian.discoverapp.R;
import com.arandasebastian.discoverapp.model.Genre;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
                inflate(R.layout.genre_view_pager_row, parent, false));
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
        private ProgressBar progressBar;

        public GenresViewHolder(@NonNull final View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.genre_recycler_row_progress_bar);
            progressBar.setVisibility(View.VISIBLE);
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
                    .placeholder(R.drawable.img_genre_placeholder).addListener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            })
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
