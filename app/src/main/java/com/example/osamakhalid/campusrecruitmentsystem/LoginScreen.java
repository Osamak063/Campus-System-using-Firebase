package com.example.osamakhalid.campusrecruitmentsystem;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import layout.CompPost;
import layout.CompanyAdmin;
import layout.CompanyList;
import layout.JobsFragment;
import layout.MyPosts;
import layout.PostJobFragment;
import layout.PostsAdmin;
import layout.StudentsAdmin;
import layout.StudentsListFragment;
import layout.UsersAdmin;

public class LoginScreen extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private List<User> users;
    private ListView loginlistview;
    private loginAdapter adapter;
    private Fragment fragment;
    private String type;
    private String name;
    private String Uid;
    private Menu mymenu;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
          Intent i= getIntent();
          this.invalidateOptionsMenu();
          type=i.getStringExtra("type");
          if(type.equals("admin")){
              fragment = new PostsAdmin();
              FragmentManager fm6 = getSupportFragmentManager();
              FragmentTransaction ft6 = fm6.beginTransaction();
              ft6.replace(R.id.fragment_p, fragment);
              ft6.commit();
          }
          if(type.equals("company")) {
              fragment = new MyPosts();
              FragmentManager fm5 = getSupportFragmentManager();
              FragmentTransaction ft5 = fm5.beginTransaction();
              ft5.replace(R.id.fragment_p, fragment);
              ft5.commit();
          }
          }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items,menu);

        if(menu!=null){
            if(type.equals("student")) {
                MenuItem item = (MenuItem) menu.findItem(R.id.studentslist);
                MenuItem item2 = (MenuItem) menu.findItem(R.id.companylist);
                MenuItem item3 = (MenuItem) menu.findItem(R.id.postjob);
                MenuItem item5= (MenuItem) menu.findItem(R.id.myposts);
                item2.setVisible(true);
                item5.setVisible(false);
                item.setVisible(false);
                item3.setVisible(false);
            }
            else if(type.equals("company")){
                MenuItem item6= (MenuItem) menu.findItem(R.id.jobs);
                MenuItem item = (MenuItem) menu.findItem(R.id.studentslist);
                MenuItem item2 = (MenuItem) menu.findItem(R.id.companylist);
                MenuItem item3 = (MenuItem) menu.findItem(R.id.postjob);
                MenuItem item5= (MenuItem) menu.findItem(R.id.myposts);
                item5.setVisible(true);
                item2.setVisible(false);
                item.setVisible(true);
                item3.setVisible(true);
                item6.setVisible(false);
            }
            else if(type.equals("admin")){
                MenuItem item3 = (MenuItem) menu.findItem(R.id.postjob);
                MenuItem item = (MenuItem) menu.findItem(R.id.studentslist);
                MenuItem item2 = (MenuItem) menu.findItem(R.id.companylist);
                MenuItem item5= (MenuItem) menu.findItem(R.id.myposts);
                item5.setVisible(false);
                item3.setVisible(false);
                item.setVisible(true);
                item2.setVisible(true);

            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.jobs:
                if(type.equals("student")) {
                    fragment = new JobsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", type);
                    fragment.setArguments(bundle);
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_p, fragment);
                    ft.commit();
                }

                else if(type.equals("admin")){
                    fragment = new PostsAdmin();
                    FragmentManager fm7 = getSupportFragmentManager();
                    FragmentTransaction ft7 = fm7.beginTransaction();
                    ft7.replace(R.id.fragment_p, fragment);
                    ft7.commit();
                }
                return true;
            case R.id.studentslist:
                if(type.equals("admin")){
                    fragment = new StudentsAdmin();
                    FragmentManager fm11 = getSupportFragmentManager();
                    FragmentTransaction ft11 = fm11.beginTransaction();
                    ft11.replace(R.id.fragment_p, fragment);
                    ft11.commit();
                }
                else {
                    fragment = new StudentsListFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("type", type);
                    fragment.setArguments(bundle1);
                    FragmentManager fm1 = getSupportFragmentManager();
                    FragmentTransaction ft1 = fm1.beginTransaction();
                    ft1.replace(R.id.fragment_p, fragment);
                    ft1.commit();
                }

                return true;
            case R.id.companylist:
                 if(type.equals("admin")){
                    fragment = new CompanyAdmin();
                    FragmentManager fm10 = getSupportFragmentManager();
                    FragmentTransaction ft10 = fm10.beginTransaction();
                    ft10.replace(R.id.fragment_p, fragment);
                    ft10.commit();
                }
                else{
                     fragment = new CompanyList();
                     FragmentManager fm2 = getSupportFragmentManager();
                     FragmentTransaction ft2 = fm2.beginTransaction();
                     ft2.replace(R.id.fragment_p, fragment);
                     ft2.commit();
                 }
                return true;
            case R.id.postjob:
                fragment = new PostJobFragment();
                FragmentManager fm3 = getSupportFragmentManager();
                FragmentTransaction ft3 = fm3.beginTransaction();
                ft3.replace(R.id.fragment_p, fragment);
                ft3.commit();
                return true;
            case R.id.myposts:
                fragment = new MyPosts();
                FragmentManager fm9 = getSupportFragmentManager();
                FragmentTransaction ft9 = fm9.beginTransaction();
                ft9.replace(R.id.fragment_p, fragment);
                ft9.commit();
                return true;
            case R.id.logout:
                firebaseAuth.getInstance().signOut();
                Intent i= new Intent(LoginScreen.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
