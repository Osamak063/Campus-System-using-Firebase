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
import com.example.osamakhalid.campusrecruitmentsystem.loginAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CompanyList extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private ListView complistview;
    private loginAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_company_list, container, false);
         firebaseDatabase=FirebaseDatabase.getInstance();
         reference=firebaseDatabase.getReference().child("Users");
         complistview=(ListView) view.findViewById(R.id.company_listview);
         adapter= new loginAdapter(getActivity(),new ArrayList<User>());
         complistview.setAdapter(adapter);
         Query query= reference;
         query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User checkuser;
                for(DataSnapshot snap:dataSnapshot.getChildren()){
                    checkuser=snap.getValue(User.class);
                    //Log.v("check","name="+checkuser.getName());
                    if(checkuser.getType().equals("company")){
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


}
