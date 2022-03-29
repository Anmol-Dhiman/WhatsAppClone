package com.example.whatsappclone.Adapters;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.whatsappclone.ChatsPage;
import com.example.whatsappclone.R;
import com.example.whatsappclone.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

//    we have just build the recycler view over here
//    which is used to show the contacts in the chats section

    ArrayList<User> list;
    Context context;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    public UserAdapter(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_sample_source, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = list.get(position);
        holder.userName.setText(user.getPhoneNumber());
        holder.lastMessage.setText("temp");
//        Picasso.get().load(user.getProfliePic()).placeholder(R.drawable.avatar).into(holder.proflieImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatsPage.class);
                intent.putExtra("userId", user.getUserId());
                Log.d("theIdOfReceiver", "" + user.getUserId());
                intent.putExtra("userName", user.getPhoneNumber());
                intent.putExtra("profilePic", user.getProfliePic());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView proflieImg;
        TextView userName, lastMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            proflieImg = itemView.findViewById(R.id.profliePic);
            userName = itemView.findViewById(R.id.userName);
            lastMessage = itemView.findViewById(R.id.lastMessage);
        }
    }
}
