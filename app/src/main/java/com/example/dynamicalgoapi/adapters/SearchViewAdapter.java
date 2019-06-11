package com.example.dynamicalgoapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dynamicalgoapi.R;
import com.example.dynamicalgoapi.UserDetailsActivity;
import com.example.dynamicalgoapi.models.ProfileRequest;
import com.example.dynamicalgoapi.models.ProfileResponse;
import com.example.dynamicalgoapi.models.User;

import java.util.List;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.MyViewHolder> {

    private List<User> users;
    private List<ProfileRequest> profileRequests;
    private Context context;

    public SearchViewAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(users.get(position).getName());
        holder.email.setText(users.get(position).getEmail());
        //holder.about.setText(users.get(position).getAboutMe());

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            final User getItemId = users.get(position);
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailsActivity.class);
                intent.putExtra("name", getItemId.getName());
                intent.putExtra("email", getItemId.getEmail());
                intent.putExtra("about", getItemId.getAboutMe());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,email, about;
        LinearLayout itemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            itemLayout=itemView.findViewById(R.id.itemViewLL);



        }
    }
}
