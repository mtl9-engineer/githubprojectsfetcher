package com.example.appprojectsfetcher.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appprojectsfetcher.R;
import com.example.appprojectsfetcher.model.Repository;

import java.util.ArrayList;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {


    private ArrayList<Repository> dataList;
    private Activity activity;

    public RepositoryAdapter(ArrayList<Repository> dataList , Activity activity) {
        this.dataList = dataList;
        this.activity=activity;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_repository, parent, false);
        return new RepositoryViewHolder(view);
    }



    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        holder.txt_repository_name.setText(dataList.get(position).getName());
        holder.txt_repository_score.setText(dataList.get(position).getScore());
        holder.txt_repository_description.setText(dataList.get(position).getDescription());
        holder.txt_owner_login.setText(dataList.get(position).getOwner().getLogin());

        Glide.with(activity).load(dataList.get(position).getOwner().getAvatarUrl()).into(holder.image_owner_avater);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {


        TextView txt_repository_name;
        TextView txt_repository_score;
        TextView txt_repository_description;
        TextView txt_owner_login;
        ImageView image_owner_avater;

        RepositoryViewHolder(View itemView) {
            super(itemView);
            txt_repository_name =  itemView.findViewById(R.id.name);
            txt_repository_score = itemView.findViewById(R.id.score);
            txt_repository_description =  itemView.findViewById(R.id.description);

            txt_owner_login =  itemView.findViewById(R.id.login);
            image_owner_avater = itemView.findViewById(R.id.avatar);
        }
    }
}
