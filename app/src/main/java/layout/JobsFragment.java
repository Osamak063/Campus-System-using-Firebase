package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.osamakhalid.campusrecruitmentsystem.Post;
import com.example.osamakhalid.campusrecruitmentsystem.R;
import com.example.osamakhalid.campusrecruitmentsystem.User;
import com.example.osamakhalid.campusrecruitmentsystem.postAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class JobsFragment extends Fragment {
    ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private DatabaseReference userref;
    private ChildEventListener childEventListener;
    private FirebaseAuth firebaseAuth;
    private postAdapter postAdapter;

    private List<Post> postList;
    private EditText postedittext;
    private Button addbutton;
    LinearLayout postingjob;
    private String type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container != null) {
            container.removeAllViews();
        }
        View view= inflater.inflate(R.layout.fragment_jobs, container, false);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        reference=firebaseDatabase.getReference().child("Posts");
        userref=firebaseDatabase.getReference().child("Users");
        listView=(ListView) view.findViewById(R.id.jobs_listview);

        postAdapter= new postAdapter(getActivity(),new ArrayList<Post>());
        listView.setAdapter(postAdapter);


    /*    addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query1= userref;
                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User checkuser=null,doneuser=null;
                        for(DataSnapshot snap:dataSnapshot.getChildren()){
                            String key=snap.getKey();
                            if(key.equals(firebaseAuth.getCurrentUser().getUid())){
                                checkuser= snap.getValue(User.class);
                                   if(!checkuser.getType().equals("student")){
                                       doneuser=checkuser;
                                   }

                            }

                            //Log.v("check","name="+checkuser.getName());
                        }
                        Post post1=new Post(doneuser.getName(),doneuser.getLocation(),doneuser.getPhonenum(),postedittext.getText().toString());
                        reference.push().setValue(post1);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
*/
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               Post checkpost= dataSnapshot.getValue(Post.class);
               postAdapter.setPost(checkpost);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        reference.addChildEventListener(childEventListener);
        return view;
    }

}
