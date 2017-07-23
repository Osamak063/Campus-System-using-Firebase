package com.example.osamakhalid.campusrecruitmentsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SeeStudentsScreen extends AppCompatActivity {
    private String Postkey;
    private loginAdapter adapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_students_screen);
        Intent intent= getIntent();
        Postkey= intent.getStringExtra("key");
        firebaseDatabase= FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Apply");
        listView= (ListView) findViewById(R.id.see_students_listview);
        adapter= new loginAdapter(SeeStudentsScreen.this,new ArrayList<User>());
        listView.setAdapter(adapter);
        Query query=reference;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Apply apply= snapshot.getValue(Apply.class);
                    if(apply.getPostkey().equals(Postkey)){
                        User user1= new User(apply.getName(),apply.getLocation(),apply.getPhonenum(),apply.getGrade(),apply.getDegree(),apply.getSkills(),apply.getType(),apply.getKey());
                        adapter.setUser(user1);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
