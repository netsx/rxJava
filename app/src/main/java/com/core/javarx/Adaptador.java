package com.core.javarx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.core.javarx.pojo.Show_;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adaptador  extends RecyclerView.Adapter<Adaptador.MyViewHolder> {

    List<Show_> show_sh ;
    Context context;


    public Adaptador(List<Show_> show_sh, Context context) {
        this.show_sh = show_sh;
        this.context = context;
    }

    @NonNull
    @Override
    public Adaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.MyViewHolder holder, int position) {

        holder.textView.setText(show_sh.get(position).getName());

        try {

            Picasso.with(context)
                    .load(show_sh.get(position).getImage().getOriginal())

                    .into(holder.imageView);

        }catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return show_sh.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView =itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
