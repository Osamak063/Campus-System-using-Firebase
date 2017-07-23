package com.example.osamakhalid.campusrecruitmentsystem;

import android.content.Context;
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

public class PostsAdminAdapter extends ArrayAdapter<Post> {
    private List<Post> posts= new ArrayList<Post>();
    TextView companyname;
    TextView location;
    TextView phonenum;
    TextView jobtitle;
    TextView description;
    Button delete_post;
    private String mtype;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase;

    public PostsAdminAdapter(@NonNull Context context, List<Post> posts) {
        super(context, R.layout.posts_admin_items,posts);
        this.posts=posts;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = convertView;
        if (customView == null) {
            customView = LayoutInflater.from(getContext()).inflate(R.layout.posts_admin_items, parent, false);
        }
        Post post= getItem(position);
        companyname=(TextView) customView.findViewById(R.id.compname_postadmin);
        location=(TextView) customView.findViewById(R.id.complocation_postadmin);
        phonenum=(TextView) customView.findViewById(R.id.contactno_postadmin);
        jobtitle=(TextView) customView.findViewById(R.id.jobtitle_postadmin);
        description=(TextView) customView.findViewById(R.id.description_postadmin);
        delete_post=(Button) customView.findViewById(R.id.delete_post_admin);
        companyname.setText(post.getCompanyname());
        location.setText(post.getLocation());
        phonenum.setText(post.getPhonenum());
        description.setText("Description : "+post.getDescription());
        jobtitle.setText("Job Title: "+post.getJobtitle());
        delete_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Post post1= getItem(position);
              //  System.out.println("deletefff   "+post1.getPostkey());
                firebaseDatabase=FirebaseDatabase.getInstance();
                posts.remove(post1);
                notifyDataSetChanged();
                reference=firebaseDatabase.getReference().child("Posts");
                reference.child(post1.getPostkey()).removeValue();
               // reference.child(post1.getKey()).removeValue();

            }
        });
        return customView;
    }

    public void setPost(Post post) {
        posts.add(post);
        notifyDataSetChanged();
    }
}
