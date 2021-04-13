package com.example.firebasetest1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etName, etImg;
    Button btnAdd, btnRead;
    TextView tvRead;

    FirebaseFirestore db; // 1. Access a Cloud Firestore instance from your Activity
    Map<String, Object> art;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        etName = findViewById(R.id.editTextTextPersonName);
        etImg = findViewById(R.id.editTextTextPersonName2);
        btnAdd = findViewById(R.id.button);
        btnRead = findViewById(R.id.button2);
        tvRead = findViewById(R.id.tvAnswer);

        db = FirebaseFirestore.getInstance(); // 1. Access a Cloud Firestore instance from your Activity
        art = new HashMap<>();
    }

    // Create a new user with a first and last name
    public void onClickAdd(View view) {
        ArtItem artItem = new ArtItem("Leonardo","gs://fir-test1-1367d.appspot.com/mona-lisa.jpg!Large.jpg");
        db.collection("arts").document("Monalisa").set(artItem);

        ArtItem artItem1 = new ArtItem("Rafael","gs://fir-test1-1367d.appspot.com/rafael.jpg");
        db.collection("arts").document("Men").set(artItem1);
    }

    public void onClickRead(View view) {
        DocumentReference docRef = db.collection("arts").document(etName.getText().toString());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ArtItem artItemRead = documentSnapshot.toObject(ArtItem.class);
                tvRead.setText(artItemRead.getAuthor() + " - " + artItemRead.getImg());
            }
        });


    }
}