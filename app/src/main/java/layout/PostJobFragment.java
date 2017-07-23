package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osamakhalid.campusrecruitmentsystem.Post;
import com.example.osamakhalid.campusrecruitmentsystem.R;
import com.example.osamakhalid.campusrecruitmentsystem.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class PostJobFragment extends Fragment {
    private EditText jobtitle;
    private EditText description;
    private Button postbutton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private DatabaseReference userref;
    private FirebaseAuth firebaseAuth;
    int count=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container != null) {
            container.removeAllViews();
        }
        View view= inflater.inflate(R.layout.fragment_post_job, container, false);
        jobtitle=(EditText) view.findViewById(R.id.jobtitle_postajob);
        description=(EditText) view.findViewById(R.id.description_postajob);
        postbutton=(Button) view.findViewById(R.id.postbutton_postajob);
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Posts");
        firebaseAuth=FirebaseAuth.getInstance();
        userref=firebaseDatabase.getReference().child("Users");
        postbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query=userref;
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User checkuser=null;
                        for(DataSnapshot snap:dataSnapshot.getChildren()){
                            String key=snap.getKey();
                            if(key.equals(firebaseAuth.getCurrentUser().getUid())){
                                checkuser= snap.getValue(User.class);
                                break;
                            }
                        }
                        ++count;
                        String key = reference.push().getKey();
                        Post post= new Post(jobtitle.getText().toString(),description.getText().toString(),checkuser.getName(),checkuser.getLocation(),checkuser.getPhonenum(),firebaseAuth.getCurrentUser().getUid(),key);
                        reference.child(key).setValue(post);
                        Toast.makeText(getActivity(),"Successfully posted",Toast.LENGTH_SHORT).show();
                        description.setText("");
                        jobtitle.setText("");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;
    }


}
