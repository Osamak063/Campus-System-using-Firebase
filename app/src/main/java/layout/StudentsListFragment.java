package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.osamakhalid.campusrecruitmentsystem.R;
import com.example.osamakhalid.campusrecruitmentsystem.User;
import com.example.osamakhalid.campusrecruitmentsystem.loginAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentsListFragment extends Fragment {

    ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private loginAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container != null) {
            container.removeAllViews();
    }
        View view =inflater.inflate(R.layout.fragment_students_list, container, false);
        Bundle bundle= getArguments();

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Users");
        listView=(ListView) view.findViewById(R.id.students_listview);
        adapter= new loginAdapter(getActivity(),new ArrayList<User>());
        listView.setAdapter(adapter);
        Query query= reference;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User checkuser;
                for(DataSnapshot snap:dataSnapshot.getChildren()){
                    checkuser=snap.getValue(User.class);
                    //Log.v("check","name="+checkuser.getName());
                    if(checkuser.getType().equals("student")){
                        adapter.setUser(checkuser);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String type = bundle.getString("type");
        }
    }
}
