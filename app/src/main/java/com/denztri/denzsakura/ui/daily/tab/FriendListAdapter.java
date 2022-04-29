package com.denztri.denzsakura.ui.daily.tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public void onBindViewHolder(@NonNull FriendListAdapter.FriendViewHolder holder, int position) {
        holder.firstName.setText(this.friendList.get(position).firstName);
        holder.lastName.setText(this.friendList.get(position).lastName);
    }

    @Override
    public int getItemCount() {
        return this.friendList.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        TextView firstName;
        TextView lastName;
        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.friend_recycle_firstname);
            lastName = itemView.findViewById(R.id.friend_recycle_lastname);
        }
    }
}
