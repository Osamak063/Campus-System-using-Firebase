package com.example.osamakhalid.campusrecruitmentsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Spinner mySpinner;
    private String categoryValue;
    private EditText name;
    private EditText email;
    private EditText password;
    private Button signupbutton;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
        mySpinner=(Spinner) findViewById(R.id.spinner);
        name=(EditText) findViewById(R.id.name_signup);
        email=(EditText) findViewById(R.id.email_signup);
        password=(EditText) findViewById(R.id.password_signup);
        signupbutton=(Button) findViewById(R.id.signup_button);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.items,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        categoryValue=mySpinner.getSelectedItem().toString();
        Toast.makeText(Register.this,"value"+categoryValue,Toast.LENGTH_SHORT).show();
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryValue=mySpinner.getItemAtPosition(i).toString();
                Toast.makeText(Register.this,"value"+categoryValue,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(Register.this,"Please enter e-mail.",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(Register.this,"Please enter password.",Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(Register.this,"Successfully registered.",Toast.LENGTH_SHORT).show();
                                        Toast.makeText(Register.this,"value check"+categoryValue,Toast.LENGTH_SHORT).show();
                                        if(categoryValue.equals("Student")){
                                            Intent i= new Intent(Register.this,StudentInformation.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            i.putExtra("name",name.getText().toString());
                                            startActivity(i);
                                            finish();
                                        }
                                        else if(categoryValue.equals("Company")){
                                            Intent i= new Intent(Register.this,CompanyInformation.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            i.putExtra("name",name.getText().toString());
                                            startActivity(i);
                                            finish();
                                        }
                                        }
                                    else {
                                        Toast.makeText(Register.this,"Failed to register.",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });

    }
}
