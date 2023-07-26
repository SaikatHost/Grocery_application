package com.example.grocery_new;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private List<MovieModel> moviesList;
    CardView card;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView p_name, p_quantity, p_price;
        ImageView p_img;
        Context context;
        MyViewHolder(View view) {
            super(view);
            p_img=view.findViewById(R.id.p_img);
            p_name = view.findViewById(R.id.p_name);
            p_price = view.findViewById(R.id.p_price);
            p_quantity = view.findViewById(R.id.p_quantity);
            card=view.findViewById(R.id.card);
        }
    }
    public MoviesAdapter(List<MovieModel> moviesList) {
        this.moviesList = moviesList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_list, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovieModel movie = moviesList.get(position);
        //holder.p_img.setImageResource(movie.getImg());
        holder.p_name.setText(movie.getTitle());
        holder.p_price.setText("₹ "+movie.getGenre());
        holder.p_quantity.setText(movie.getYear());
        holder.context=movie.getContext();
        Picasso.with(holder.context).load(movie.getImg())
                .into(holder.p_img);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(holder.context,"hello "+movie.getTitle(),Toast.LENGTH_SHORT).show();
                Intent i=new Intent(holder.context,show_product.class);
                i.putExtra("p_img",movie.getImg());
                i.putExtra("p_name",movie.getTitle());
                i.putExtra("p_quantity",movie.getYear());
                i.putExtra("p_price",movie.getGenre());
                i.putExtra("p_des",movie.getDescription());
                holder.context.startActivity(i);
                //finish();
            }
        });
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}