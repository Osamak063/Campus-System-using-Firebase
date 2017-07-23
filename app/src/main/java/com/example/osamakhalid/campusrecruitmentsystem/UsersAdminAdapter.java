package com.example.osamakhalid.campusrecruitmentsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama Khalid on 7/19/2017.
 */

public class UsersAdminAdapter extends ArrayAdapter<User> {
    private List<User> users=new ArrayList<User>();
    TextView heading;
    TextView grade;
    TextView skills;
    TextView degree;
    Button delete_button;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private DatabaseReference postref;
    public UsersAdminAdapter(@NonNull Context context, List<User> users) {
        super(context, R.layout.users_admin_items,users);
        this.users=users;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = convertView;
        if (customView == null) {
            customView = LayoutInflater.from(getContext()).inflate(R.layout.users_admin_items, parent, false);
        }
        final User user = getItem(position);
        heading=(TextView) customView.findViewById(R.id.heading_admin);
        grade=(TextView) customView.findViewById(R.id.gradeorlocationlogin_admin);
        skills=(TextView) customView.findViewById(R.id.skillsorpostlogin_admin);
        degree=(TextView) customView.findViewById(R.id.degreeornumlogin_admin);
        delete_button=(Button) customView.findViewById(R.id.delete_user_admin);
        if(user.getType().equals("student")) {
            skills.setVisibility(View.VISIBLE);
            heading.setText("Student: " + user.getName());
            grade.setText("Grade/CGPA: " + user.getGrade());
            skills.setText("Skills: " + user.getSkills());
            degree.setText("Degree: " + user.getDegree());
        }
        else if(user.getType().equals("company")){
            heading.setText("Company Name: " + user.getName());
            grade.setText("Location: " + user.getLocation());
            degree.setText("Contact No.: " + user.getPhonenum());
            skills.setVisibility(View.INVISIBLE);
        }
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user1= getItem(position);
                firebaseDatabase=FirebaseDatabase.getInstance();
                reference=firebaseDatabase.getReference().child("Users");
                postref=firebaseDatabase.getReference().child("Posts");
                reference.child(user1.getKey()).removeValue();
                users.remove(user1);
                notifyDataSetChanged();
                Query query=postref;
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                            Post post=snapshot.getValue(Post.class);
                            if(post.getKey().equals(user.getKey())){
                                postref.child(post.getPostkey()).removeValue();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        return customView;
    }

    public void setUser(User user) {
        users.add(user);
        notifyDataSetChanged();
    }
}
