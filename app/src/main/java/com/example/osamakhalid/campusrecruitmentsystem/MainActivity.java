package com.example.osamakhalid.campusrecruitmentsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
 private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Spinner mySpinner;
    private String categoryValue="";
    private EditText email;
    private EditText password;
    private Button loginbutton;
    private ProgressDialog progressDialog;
    private String emailadmin="admin@campus.com";
    private String passwordadmin="12345++";
    private Boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flag=false;
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        progressDialog= new ProgressDialog(this);
        databaseReference=firebaseDatabase.getReference().child("Users");
        if(firebaseAuth.getCurrentUser()!=null) {
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            if (firebaseAuth.getCurrentUser().getUid().equals("nOxMOzdVxAbvrgi2ZMKZEnd4vD22")) {
                progressDialog.dismiss();
                Intent iadmin = new Intent(MainActivity.this, LoginScreen.class);
                iadmin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                iadmin.putExtra("type", "admin");
                Toast.makeText(MainActivity.this, "Signed In.", Toast.LENGTH_SHORT).show();
                startActivity(iadmin);
                finish();
            } else {
                databaseReference.orderByChild("key").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        User user = dataSnapshot.getValue(User.class);
                        System.out.println("oocoming  "+user.getName());
                        progressDialog.dismiss();
                        flag=true;
                       // System.out.println("Check0"+user.getType());
                        Toast.makeText(MainActivity.this, "Signed In.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, LoginScreen.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("type", user.getType());
                        startActivity(i);
                        finish();
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
                });
                //progressDialog.dismiss();
               // Toast.makeText(MainActivity.this, "Failed to Sign In.", Toast.LENGTH_SHORT).show();
            /*    databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("type");
                Query query = databaseReference;
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        System.out.println("aaaaa");
                        String b=dataSnapshot.getValue(String.class);
                        System.out.println("checkfffbb"+b);
                        Toast.makeText(MainActivity.this, "Signed In.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, LoginScreen.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("type", b);
                        startActivity(i);
                        finish();
                    }
*/
                   /* @Override
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
                });*/
                   if(flag==false) {
                       System.out.println("oocoming1");
                   }
            }
        }
            loginbutton=(Button) findViewById(R.id.loginbutton);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);

        Button signup=(Button) findViewById(R.id.signupbutton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(MainActivity.this,Register.class);
                i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i1);
                finish();
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please enter e-mail.",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please enter password.",Toast.LENGTH_SHORT).show();
                }
                else{
                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    progressDialog.dismiss();
                                    if(emailadmin.equals(email.getText().toString()) && passwordadmin.equals(password.getText().toString())){
                                        Intent iadmin= new Intent(MainActivity.this,LoginScreen.class);
                                        iadmin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        iadmin.putExtra("type","admin");
                                        Toast.makeText(MainActivity.this, "Signed In.", Toast.LENGTH_SHORT).show();
                                        startActivity(iadmin);
                                        finish();
                                    }
                                    else {
                                        databaseReference=firebaseDatabase.getReference().child("Users");
                                        Query query = databaseReference;
                                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                User user=null;
                                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                    user=snapshot.getValue(User.class);
                                                    if (firebaseAuth.getCurrentUser().getUid().equals(snapshot.getKey())) {
                                                        user = snapshot.getValue(User.class);
                                                        break;
                                                    }
                                                }
                                                Toast.makeText(MainActivity.this, "Signed In.", Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(MainActivity.this, LoginScreen.class);
                                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                i.putExtra("type", user.getType());
                                                startActivity(i);
                                                finish();
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }

                                }
                                else
                                {   progressDialog.dismiss();
                                    Toast.makeText(MainActivity.this,"Failed to Sign In, please try again",Toast.LENGTH_SHORT).show();  }
                            }
                        });
                }
            }
        });
    }
}
