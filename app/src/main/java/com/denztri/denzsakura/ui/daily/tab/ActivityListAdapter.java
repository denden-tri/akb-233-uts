package com.denztri.denzsakura.ui.daily.tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.db.Activity;

import java.util.List;

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder> {
    private final Context context;
    private List<Activity> activityList;

    public ActivityListAdapter(Context context){
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setActivityList(List<Activity> list){
        this.activityList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActivityListAdapter.ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_activity_row, parent, false);

        return new ActivityViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ActivityListAdapter.ActivityViewHolder holder, int position) {
        holder.activityName.setText(this.activityList.get(position).activityName);
        holder.img
                .setImageDrawable(context.getResources()
                        .getDrawable(getDrawableIdByName(this.activityList.get(position)
                                .drawableName)));
    }

    @Override
    public int getItemCount() {
        return this.activityList.size();
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView activityName;
        ImageView img;
        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            activityName = itemView.findViewById(R.id.activity_name);
            img = itemView.findViewById(R.id.activity_recycle_img);
        }
    }

    private int getDrawableIdByName(String name){
        Resources res = context.getResources();
        return res.getIdentifier(name, "drawable", context.getPackageName());
    }
}