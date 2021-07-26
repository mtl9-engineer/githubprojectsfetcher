package com.example.appprojectsfetcher.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appprojectsfetcher.R;
import com.example.appprojectsfetcher.model.Item;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<Item> dataList;
    private Activity activity;
    private boolean isLoadingAdded = false;

    public RepositoryAdapter(List<Item> dataList , Activity activity) {
        this.dataList = dataList;
        this.activity=activity;
    }

    public List<Item> getDataList() {
        return dataList;
    }

    public void setDataList(List<Item> dataList) {
        this.dataList = dataList;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
    }
    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = dataList.size() - 1;
        Item item = getItem(position);
        if (item != null) {
            dataList.remove(position);
            notifyItemRemoved(position);
        }
    }
    public Item getItem(int position) {
        return dataList.get(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM:
                viewHolder =getViewHolder(parent, layoutInflater);
                break;
            case LOADING:
                View v2 = layoutInflater.inflate(R.layout.progress_bar, parent, false);
                viewHolder = new LoadingViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        switch (getItemViewType(position)) {
            case ITEM:
                RepositoryViewHolder item = (RepositoryViewHolder) holder;
                 item.txt_repository_name.setText(dataList.get(position).getName());

                if( dataList.get(position).getStargazers_count() >= 1000){
                    float stars=dataList.get(position).getStargazers_count() ;
                    stars = stars / 1000 ;
                    item.txt_repository_score.setText(String.valueOf(stars)+R.string.k);
                }
                else{
                    item.txt_repository_score.setText(String.valueOf(dataList.get(position).getStargazers_count()));
                }
                item.txt_repository_description.setText(dataList.get(position).getDescription());
                item.txt_owner_login.setText(dataList.get(position).getOwner().getLogin());

                Glide.with(activity).load(dataList.get(position).getOwner().getAvatar_url()).into(item.image_owner_avater);
                break;
            case LOADING:
                break;
        }
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View view= inflater.inflate(R.layout.row_repository, parent, false);
        viewHolder = new RepositoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == dataList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {

        return dataList == null ? 0 :dataList.size() ;
    }


    protected class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
    protected class RepositoryViewHolder extends RecyclerView.ViewHolder {
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
