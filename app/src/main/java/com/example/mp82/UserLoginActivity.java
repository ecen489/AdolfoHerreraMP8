package com.example.mp82;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserLoginActivity extends AppCompatActivity {

    DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
    private EditText queryInput;
    private Button queryOne, queryTwo, push ,signOut;
    private ListView list;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    Intent ShopIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        setupInterface();
        ShopIntent = new Intent(this, PushActivity.class);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);

    }

    private void setupInterface() {
        queryInput = findViewById(R.id.queryText);
        queryOne = findViewById(R.id.query1);
        queryTwo = findViewById(R.id.query2);
        push = findViewById(R.id.push);
        signOut = findViewById(R.id.signOut);
        list = findViewById(R.id.outputList);

    }

    public void clickQueryOne(View v) {
        DatabaseReference table = fb.child("simpsons/grades");
        String input = queryInput.getText().toString().trim();
        Query something = table.orderByChild("student_id").equalTo(Integer.parseInt(input));
        arrayList.clear();
        something.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot student : dataSnapshot.getChildren()) {
                    Grade grade = student.getValue(Grade.class);
                    String push = grade.course_name + "  " + grade.grade;
                    arrayList.add(push);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        list.setAdapter(adapter);

    }

    public void clickQueryTwo(View v) {
        DatabaseReference table = fb.child("simpsons/grades");
        String input = queryInput.getText().toString().trim();
        Query something = table.orderByChild("student_id").startAt(Integer.parseInt(input));

        arrayList.clear();

        something.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot student : dataSnapshot.getChildren()) {
                    Grade grade = student.getValue(Grade.class);

                    String push;

                    if (grade.student_id == 123) {
                        push = "Bart, " + grade.course_name + ", " + grade.grade;
                    } else if(grade.student_id == 404) {
                        push = "Ralph, " + grade.course_name + ", " + grade.grade;
                    } else if(grade.student_id == 456) {
                        push = "Milhouse, " + grade.course_name + ", " + grade.grade;
                    } else {
                        push = "Lisa, " + grade.course_name + ", " + grade.grade;
                    }
                    arrayList.add(push);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        list.setAdapter(adapter);
    }

    public void clickPush(View v) {
        startActivity(ShopIntent);
        finish();
    }

    public void clickSignOut(View v) {

    }


}
