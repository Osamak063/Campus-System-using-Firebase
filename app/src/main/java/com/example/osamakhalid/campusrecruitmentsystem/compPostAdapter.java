package com.example.osamakhalid.campusrecruitmentsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama Khalid on 7/19/2017.
 */

public class compPostAdapter extends ArrayAdapter<Post> {
    private List<Post> posts= new ArrayList<Post>();
    TextView companyname;
    TextView location;
    TextView phonenum;
    TextView jobtitle;
    TextView description;
    private String mtype;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    public compPostAdapter(@NonNull Context context, List<Post> posts) {

        super(context,R.layout.comp_post_items,posts);
        this.posts=posts;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = convertView;
        if (customView == null) {
            customView = LayoutInflater.from(getContext()).inflate(R.layout.comp_post_items, parent, false);
        }
        Post post= getItem(position);
        companyname=(TextView) customView.findViewById(R.id.compname_postComp);
        location=(TextView) customView.findViewById(R.id.complocation_postComp);
        phonenum=(TextView) customView.findViewById(R.id.contactno_postComp);
        jobtitle=(TextView) customView.findViewById(R.id.jobtitle_postComp);
        description=(TextView) customView.findViewById(R.id.description_postComp);

        companyname.setText(post.getCompanyname());
        location.setText(post.getLocation());
        phonenum.setText(post.getPhonenum());
        description.setText("Description : "+post.getDescription());
        jobtitle.setText("Job Title: "+post.getJobtitle());

        return customView;
    }

    public void setPost(Post post) {
        posts.add(post);
        notifyDataSetChanged();
    }

}
