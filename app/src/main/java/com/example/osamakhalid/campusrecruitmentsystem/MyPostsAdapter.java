package com.example.osamakhalid.campusrecruitmentsystem;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama Khalid on 7/20/2017.
 */

public class MyPostsAdapter extends ArrayAdapter<Post> {
    private List<Post> posts= new ArrayList<Post>();;
    TextView jobtitle;
    TextView description;
    private String mtype;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase;
    private Button see_students;
    private Context mcontext;
    public MyPostsAdapter(@NonNull Context context, List<Post> posts) {
        super(context,R.layout.my_posts_items,posts);
        this.posts=posts;
        mcontext=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = convertView;
        if (customView == null) {
            customView = LayoutInflater.from(getContext()).inflate(R.layout.my_posts_items, parent, false);
        }
        Post post= getItem(position);
        jobtitle=(TextView) customView.findViewById(R.id.jobtitle_mypost);
        description=(TextView) customView.findViewById(R.id.description_mypost);
        see_students=(Button) customView.findViewById(R.id.see_students_button);
        see_students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Post post1= getItem(position);
             Intent intent= new Intent(mcontext,SeeStudentsScreen.class);
             intent.putExtra("key",post1.getPostkey());
             mcontext.startActivity(intent);
            }
        });
        description.setText("Description : "+post.getDescription());
        jobtitle.setText("Job Title: "+post.getJobtitle());

        return customView;
    }

    public void setPost(Post post) {
        posts.add(post);
        notifyDataSetChanged();
    }
}
