package com.example.android.inclassassignment09_doraj;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference projectRef = database.getReference("Set project");
    private DatabaseReference mProjectRef = database.getReference("Multiple Projects");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView)findViewById(R.id.display_project);

    }

    public void setProject(View view){
        projectRef.setValue(new Project(R.string.screen_iphone_name,R.string.level_b2,R.string.screen_iphone_time));
        projectRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Project p = dataSnapshot.getValue(Project.class);
                display.setText(p.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error Loading FireBase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addProject(View view){
        mProjectRef.push().setValue(new Project(R.string.ispring_name,R.string.level_i2,R.string.ispring_time));
    }
}
