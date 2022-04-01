package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.whatsappclone.Adapters.UserAdapter;
import com.example.whatsappclone.databinding.ActivityChatAndGrpCreationBinding;
import com.example.whatsappclone.databinding.ActivityChatsPageBinding;
import com.example.whatsappclone.databinding.FragmentChatsBinding;
import com.example.whatsappclone.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatAndGrpCreation extends AppCompatActivity {

    private ActivityChatAndGrpCreationBinding binding;
    private ArrayList<User> list = new ArrayList<>();
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatAndGrpCreationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UserAdapter adapter = new UserAdapter(list, ChatAndGrpCreation.this);
        binding.chatAndGrpRecyclerView.setAdapter(adapter);
        binding.chatAndGrpRecyclerView.setLayoutManager(new LinearLayoutManager(ChatAndGrpCreation.this));

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                r0AvgZdbmCSUYjJAZo9XNFrQ5k93
                list.clear();
                list.add(new User());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    user.setUserId(dataSnapshot.getKey());
                    Log.d("data", dataSnapshot.getKey());

//                    if (user.getUserId().equals(auth.getUid())) {
//                        Log.d("userIdinchat", user.getUserId());
//                        Log.d("currentUserId", auth.getUid());
//                    } else {
                    list.add(user);

//                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}