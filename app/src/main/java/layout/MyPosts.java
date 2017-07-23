package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.osamakhalid.campusrecruitmentsystem.MyPostsAdapter;
import com.example.osamakhalid.campusrecruitmentsystem.Post;
import com.example.osamakhalid.campusrecruitmentsystem.R;
import com.example.osamakhalid.campusrecruitmentsystem.postAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyPosts extends Fragment {

    private ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private DatabaseReference userref;
    private ChildEventListener childEventListener;
    private FirebaseAuth firebaseAuth;
    private MyPostsAdapter adapter;
    private com.example.osamakhalid.campusrecruitmentsystem.postAdapter postAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (container != null) {
            container.removeAllViews();
        }

        View view=inflater.inflate(R.layout.fragment_my_posts, container, false);
        listView=(ListView) view.findViewById(R.id.myposts_listview);
        adapter= new MyPostsAdapter(getActivity(),new ArrayList<Post>());
        listView.setAdapter(adapter);
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Posts");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Post checkpost= dataSnapshot.getValue(Post.class);
                if(checkpost.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    adapter.setPost(checkpost);
                }

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
