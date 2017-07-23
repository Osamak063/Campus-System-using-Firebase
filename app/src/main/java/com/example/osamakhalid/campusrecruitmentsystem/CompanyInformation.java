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

public class CompanyInformation extends AppCompatActivity {
    private Button done;
    private EditText location_edittext;
    private EditText contactno_edittext;
    String name;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_information);

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Users");
        firebaseAuth= FirebaseAuth.getInstance();
        location_edittext=(EditText) findViewById(R.id.location_company);
        contactno_edittext=(EditText) findViewById(R.id.phone_number_company);
        done= (Button) findViewById(R.id.comp_Done);
        Intent i= getIntent();
        name=i.getStringExtra("name");
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user= new User(name,location_edittext.getText().toString(),contactno_edittext.getText().toString(),"","","","company",firebaseAuth.getCurrentUser().getUid());
                reference.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
                Intent i = new Intent(CompanyInformation.this, LoginScreen.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("type","company");
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(CompanyInformation.this,"Please complete your profile",Toast.LENGTH_SHORT).show();
    }
}
