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
import com.example.osamakhalid.campusrecruitmentsystem.PostsAdminAdapter;
import com.example.osamakhalid.campusrecruitmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class PostsAdmin extends Fragment {
    private ListView post_listview;
    private PostsAdminAdapter postsAdminAdapter;
    private ChildEventListener childEventListener;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view= inflater.inflate(R.layout.fragment_posts_admin, container, false);
        post_listview=(ListView) view.findViewById(R.id.posts_admin_listview);
         firebaseDatabase=FirebaseDatabase.getInstance();
         reference=firebaseDatabase.getReference().child("Posts");
        postsAdminAdapter= new PostsAdminAdapter(getActivity(),new ArrayList<Post>());
        post_listview.setAdapter(postsAdminAdapter);

         childEventListener = new ChildEventListener() {
             @Override
             public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                 Post checkpost= dataSnapshot.getValue(Post.class);
                 postsAdminAdapter.setPost(checkpost);
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
