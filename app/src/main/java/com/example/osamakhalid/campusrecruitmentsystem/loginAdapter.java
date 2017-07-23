package com.example.osamakhalid.campusrecruitmentsystem;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama Khalid on 7/14/2017.
 */

public class loginAdapter  extends ArrayAdapter<User>{
    private List<User> users=new ArrayList<User>();
    TextView heading;
    TextView grade;
    TextView skills;
    TextView degree;
    public loginAdapter(@NonNull Context context, List<User> users) {
        super(context, R.layout.login_items,users);
        this.users=users;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = convertView;
        if (customView == null) {
            customView = LayoutInflater.from(getContext()).inflate(R.layout.login_items, parent, false);
        }
        User user = getItem(position);
        heading=(TextView) customView.findViewById(R.id.heading);
        grade=(TextView) customView.findViewById(R.id.gradeorlocationlogin);
        skills=(TextView) customView.findViewById(R.id.skillsorpostlogin);
        degree=(TextView) customView.findViewById(R.id.degreeornumlogin);
        if(user.getType().equals("student")) {
            skills.setVisibility(View.VISIBLE);
            heading.setText("Student: " + user.getName());
            grade.setText("Grade/CGPA: " + user.getGrade());
            skills.setText("Skills: " + user.getSkills());
            degree.setText("Degree: " + user.getDegree());
        }
        else if(user.getType().equals("company")){
            heading.setText("Company Name: " + user.getName());
            grade.setText("Location: " + user.getLocation());
            degree.setText("Contact No.: " + user.getPhonenum());
            skills.setVisibility(View.INVISIBLE);
        }
        return customView;
    }

    public void setUser(User user) {
        users.add(user);
        notifyDataSetChanged();
    }
}
