package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.osamakhalid.campusrecruitmentsystem.R;
import com.example.osamakhalid.campusrecruitmentsystem.User;
import com.example.osamakhalid.campusrecruitmentsystem.UsersAdminAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class StudentsAdmin extends Fragment {

    private ListView listView_users_admin;
    private UsersAdminAdapter adapter;
    private ChildEventListener childEventListener;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (container != null) {
            container.removeAllViews();
        }
        View view=inflater.inflate(R.layout.fragment_students_admin, container, false);
        firebaseDatabase= FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Users");
        listView_users_admin= (ListView) view.findViewById(R.id.students_admin_listview);
        adapter= new UsersAdminAdapter(getActivity(),new ArrayList<User>());
        listView_users_admin.setAdapter(adapter);
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user1= dataSnapshot.getValue(User.class);
                if(user1.getType().equals("student")){
                    adapter.setUser(user1);
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
