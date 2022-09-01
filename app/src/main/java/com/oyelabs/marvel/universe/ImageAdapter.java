package com.oyelabs.marvel.universe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ArrayList<String> imageList,heroName;
    private ArrayList<Integer>idList;
    private Context context;

    public ImageAdapter(ArrayList<String> imageList,ArrayList<String> heroName, ArrayList<Integer> idList,Context context) {
        this.imageList = imageList;
        this.heroName=heroName;
        this.idList=idList;
        this.context = context;
    }
    public void setFilteredList(ArrayList<String> imageList,ArrayList<String> heroName, ArrayList<Integer> idList,Context context)
    {
        this.imageList = imageList;
        this.heroName=heroName;
        this.idList=idList;
        this.context = context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvel_charecter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // loading the images from the position
        Glide.with(holder.itemView.getContext()).load(imageList.get(position)).into(holder.imageView);
        holder.nameTextView.setText(heroName.get(position));
        String message1 = String.valueOf(idList.get(position));
        String message2 = holder.nameTextView.getText().toString();
        String message3 = imageList.get(position).toString();
        holder.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), HeroDetails.class);
                intent.putExtra("hero_id", message1);
                intent.putExtra("hero_name", message2);
                intent.putExtra("hero_image", message3);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.marvel_image);
            nameTextView=itemView.findViewById(R.id.mavel_name);
        }
    }
}

