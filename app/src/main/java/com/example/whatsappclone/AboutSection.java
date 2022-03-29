package com.example.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.whatsappclone.databinding.ActivityAboutSectionBinding;
import com.example.whatsappclone.model.User;
import com.google.firebase.database.FirebaseDatabase;

public class AboutSection extends AppCompatActivity {

    private ActivityAboutSectionBinding binding;
    private String phoneNumber;

    private FirebaseDatabase database;
    private User user;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutSectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();


        phoneNumber = getIntent().getStringExtra("phoneNumber");
        id = getIntent().getStringExtra("id");


        binding.conformationFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.about.getText().toString().trim().isEmpty()) {
                    user = new User(phoneNumber, "Hey there I am using SherlockVARM's WhatsApp" );
                } else {
                    user = new User(phoneNumber, binding.about.getText().toString().trim() );
                }

                database.getReference().child("Users").child(id).setValue(user);
                Intent intent = new Intent(AboutSection.this, MainActivity.class);
                intent.putExtra("id", id);
                Log.d("theIdOfSender", id);
                startActivity(intent);
            }
        });


    }
}