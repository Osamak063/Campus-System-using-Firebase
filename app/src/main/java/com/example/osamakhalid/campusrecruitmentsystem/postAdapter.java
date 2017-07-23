package com.example.osamakhalid.campusrecruitmentsystem;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by Osama Khalid on 7/15/2017.
 */

public class postAdapter extends ArrayAdapter<Post> {
    private List<Post> posts= new ArrayList<Post>();
    TextView companyname;
    TextView location;
    TextView phonenum;
    TextView jobtitle;
    TextView description;
    Button applybutton;
    private String mtype;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference applyreference;
    private DatabaseReference userref;
    private FirebaseDatabase firebaseDatabase;
    User user1=null;
    Context mcontext;
    public postAdapter(@NonNull Context context, List<Post> posts) {

        super(context,R.layout.post_items,posts);
        this.posts=posts;
        mcontext=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = convertView;
        if (customView == null) {
            customView = LayoutInflater.from(getContext()).inflate(R.layout.post_items, parent, false);
        }
        Post post= getItem(position);
        companyname=(TextView) customView.findViewById(R.id.compname_post);
        location=(TextView) customView.findViewById(R.id.complocation_post);
        phonenum=(TextView) customView.findViewById(R.id.contactno_post);
        jobtitle=(TextView) customView.findViewById(R.id.jobtitle_post);
        description=(TextView) customView.findViewById(R.id.description_post);
        applybutton=(Button) customView.findViewById(R.id.apply_button);
        companyname.setText(post.getCompanyname());
        location.setText(post.getLocation());
        phonenum.setText(post.getPhonenum());
        description.setText("Description : "+post.getDescription());
        jobtitle.setText("Job Title: "+post.getJobtitle());
        applybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final Post post1= getItem(position);

                firebaseDatabase=FirebaseDatabase.getInstance();
                applyreference=firebaseDatabase.getReference().child("Apply");
                userref=firebaseDatabase.getReference().child("Users");
                firebaseAuth=FirebaseAuth.getInstance();
                Query query=userref;
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                            if(snapshot.getKey().equals(firebaseAuth.getCurrentUser().getUid())){
                                user1=snapshot.getValue(User.class);
                                Apply apply= new Apply(user1.getName(),user1.getLocation(),user1.getPhonenum(),user1.getGrade(),user1.getDegree(),user1.getSkills(),user1.getType(),user1.getKey(),post1.getPostkey());
                                applyreference.push().setValue(apply);
                                Toast.makeText(mcontext,"Applied",Toast.LENGTH_LONG).show();
                                break;
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

    public void setPost(Post post) {
        posts.add(post);
        notifyDataSetChanged();
    }
}
