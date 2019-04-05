package com.example.mp82;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PushActivity extends AppCompatActivity {
    private RadioGroup rg;
    private RadioButton rb;
    private EditText courseName, courseId, courseGrade;
    private Button pushButton;

    DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
    Intent ShopIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        setupInterface();
        ShopIntent = new Intent(this, UserLoginActivity.class);
    }

    private void setupInterface() {
        rg = findViewById(R.id.rg);
        courseName = findViewById(R.id.courseName);
        courseId = findViewById(R.id.courseId);
        courseGrade = findViewById(R.id.grade);
        pushButton = findViewById(R.id.push);
    }


    public void PushClick(View v) {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
        String song = (String) rb.getText();

        String name = courseName.getText().toString().trim();
        int id = Integer.parseInt(courseId.getText().toString().trim());
        String grade = courseGrade.getText().toString().trim();
        DatabaseReference table = fb.child("simpsons/grades");
        Grade gradeObj;
        switch (song) {
            case "Bart":
                gradeObj = new Grade(id,name,grade,123);
                table.child("123").setValue(gradeObj);
                break;
            case "Ralph":
                gradeObj = new Grade(id,name,grade,404);
                table.child("404").setValue(gradeObj);
                break;
            case "Milhouse":
                gradeObj = new Grade(id,name,grade,456);
                table.child("456").setValue(gradeObj);
                break;
            case "Lisa":
                gradeObj = new Grade(id,name,grade,888);
                table.child("888").setValue(gradeObj);
                break;
        }

        startActivity(ShopIntent);
        finish();

    }
}
