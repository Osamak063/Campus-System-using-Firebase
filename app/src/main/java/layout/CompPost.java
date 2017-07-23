package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.osamakhalid.campusrecruitmentsystem.Post;
import com.example.osamakhalid.campusrecruitmentsystem.R;
import com.example.osamakhalid.campusrecruitmentsystem.compPostAdapter;
import com.example.osamakhalid.campusrecruitmentsystem.postAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class CompPost extends Fragment {
    ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private DatabaseReference userref;
    private ChildEventListener childEventListener;
    private FirebaseAuth firebaseAuth;
    private compPostAdapter postAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container != null) {
            container.removeAllViews();
        }
        View view= inflater.inflate(R.layout.fragment_comp_post, container, false);
        firebaseDatabase= FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        reference=firebaseDatabase.getReference().child("Posts");
        //userref=firebaseDatabase.getReference().child("Users");
        listView=(ListView) view.findViewById(R.id.compjobs_listview);

        postAdapter= new compPostAdapter(getActivity(),new ArrayList<Post>());
        listView.setAdapter(postAdapter);
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
