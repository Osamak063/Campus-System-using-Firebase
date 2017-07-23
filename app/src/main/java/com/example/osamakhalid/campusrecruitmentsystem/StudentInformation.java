package com.example.osamakhalid.campusrecruitmentsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentInformation extends AppCompatActivity {
    private Button done;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private String name;
    private EditText contactno;
    private EditText grade;
    private EditText degree;
    private EditText skills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        firebaseDatabase= FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Users");
        firebaseAuth= FirebaseAuth.getInstance();
        done= (Button) findViewById(R.id.student_Done);
        contactno=(EditText) findViewById(R.id.phone_number_student);
        grade=(EditText) findViewById(R.id.grade_student);
        degree=(EditText) findViewById(R.id.degree_student);
        skills=(EditText) findViewById(R.id.skills_student);
        Intent i= getIntent();
        name=i.getStringExtra("name");
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user= new User(name,"",contactno.getText().toString(),grade.getText().toString(),degree.getText().toString(),skills.getText().toString(),"student",firebaseAuth.getCurrentUser().getUid());
                reference.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
                Intent i = new Intent(StudentInformation.this, LoginScreen.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("type","student");
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(StudentInformation.this,"Please complete your profile",Toast.LENGTH_SHORT).show();
    }
}
