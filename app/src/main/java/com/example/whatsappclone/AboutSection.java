package com.example.whatsappclone;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappclone.databinding.ActivityAboutSectionBinding;
import com.example.whatsappclone.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AboutSection extends AppCompatActivity {

    private ActivityAboutSectionBinding binding;
    private String phoneNumber;
    private ActivityResultLauncher<String> launcher;
    private FirebaseDatabase database;
    private User user;
    private String id;
    private FirebaseStorage storage;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutSectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        phoneNumber = getIntent().getStringExtra("phoneNumber");
        id = getIntent().getStringExtra("id");


//                if (binding.about.getText().toString().trim().isEmpty()) {
//                    user = new User(phoneNumber, "Hey there I am using SherlockVARM's WhatsApp" );
//                } else {
//                    user = new User(phoneNumber, binding.about.getText().toString().trim() );
//                }

//                database.getReference().child("Users").child(id).setValue(user);
//                Intent intent = new Intent(AboutSection.this, MainActivity.class);
//                intent.putExtra("id", id);
//                Log.d("theIdOfSender", id);
//                startActivity(intent);

        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.profileImage.setImageURI(result);
//                        till here we are getting the image from the files


//                        in these lines we are storing the data into the cloud database
                        final StorageReference reference = storage.getReference().child("Proflie Images").child(id);
                        reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                here we do one thing we will update the url into the database of the image which have been uploaded
//                           for that we need to first download the url from the storage
                                Log.d("imageUploaded", "task completed");
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        url = uri.toString();
                                        Log.d("url",url);
                                        Picasso.get().load(url).into(binding.profileImage);
                                    }
                                });
                            }
                        });
                    }
                });

//        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String image = snapshot.getValue(String.class);
//
//            this library is used to set the image from the url
//                Picasso.get().load(image).into(binding.profileImage);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");
            }
        });
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.about.getText().toString().trim().isEmpty()) {
                    user = new User(url, phoneNumber, "Hey there I am using SherlockVARM's WhatsApp");
                } else {
                    user = new User(url, phoneNumber, binding.about.getText().toString().trim());
                }
                database.getReference().child("Users").child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent = new Intent(AboutSection.this, MainActivity.class);
                        intent.putExtra("id", id);
                        Log.d("theIdOfSender", id);
                        startActivity(intent);
                    }
                });

            }
        });

    }


}
