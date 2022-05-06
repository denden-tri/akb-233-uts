package com.denztri.denzsakura.ui.daily.tab.friend;

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
import com.denztri.denzsakura.db.Friend;

import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendViewHolder> {
    private final Context context;
    private List<Friend> friendList;

    public FriendListAdapter(Context context){
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFriendList(List<Friend> list){
        this.friendList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FriendListAdapter.FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_friend_row, parent, false);

        return new FriendViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull FriendListAdapter.FriendViewHolder holder, int position) {
        holder.firstName.setText(this.friendList.get(position).firstName);
        holder.lastName.setText(this.friendList.get(position).lastName);
        holder.img
                .setImageDrawable(context.getResources()
                        .getDrawable(getDrawableIdByName(this.friendList.get(position)
                        .drawableName)));
    }

    @Override
    public int getItemCount() {
        if (friendList != null) {
            return this.friendList.size();
        } else {
            return 0;
        }
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        TextView firstName;
        TextView lastName;
        ImageView img;
        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.friend_recycle_firstname);
            lastName = itemView.findViewById(R.id.friend_recycle_lastname);
            img = itemView.findViewById(R.id.friend_recycle_img);
        }
    }

    private int getDrawableIdByName(String name){
        Resources res = context.getResources();
        return res.getIdentifier(name, "drawable", context.getPackageName());
    }
}
