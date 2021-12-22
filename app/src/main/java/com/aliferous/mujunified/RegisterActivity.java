package com.aliferous.mujunified;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText username,email,password,course,year,section;
    CheckBox male,female,student,teacher;
    Button btn_register;


    FirebaseAuth auth;
    FirebaseFirestore reference = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.reg_et_username);
        email = findViewById(R.id.reg_et_email);
        password = findViewById(R.id.reg_et_pass);
        course = findViewById(R.id.reg_et_course);
        year = findViewById(R.id.reg_et_year);
        section = findViewById(R.id.reg_et_section);
        male = findViewById(R.id.reg_checkBox_male);
        female = findViewById(R.id.reg_checkBox_female);
        student = findViewById(R.id.reg_checkBox_student);
        teacher = findViewById(R.id.reg_checkBox_faculty);
        btn_register = findViewById(R.id.reg_button);

        auth = FirebaseAuth.getInstance();


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_course = course.getText().toString();
                String txt_year = year.getText().toString();
                String txt_section = section.getText().toString();
                String designation = null, gender = null;

                if(TextUtils.isEmpty(txt_username)  ||  TextUtils.isEmpty(txt_password)  ||  TextUtils.isEmpty(txt_email)){
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else if (txt_password.length()<6){
                    Toast.makeText(RegisterActivity.this, "Password must be atleast 6 characters long", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (male.isChecked())
                        gender = "Male";
                    else if (female.isChecked())
                        gender = "Female";

                    if (student.isChecked())
                        designation = "Student";
                    else if (teacher.isChecked())
                        designation = "Faculty";

                    register(txt_username,txt_email,txt_password,txt_course,txt_year,txt_section,designation,gender);
                }
            }
        });

    }

    private void register(final String username, final String email, final String password, final String course, final String year, final String section, final String designation, final String gender) {

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username);
                            hashMap.put("gender", gender);
                            hashMap.put("email", email);
                            hashMap.put("password", password);

                            if (designation.equals("Student"))
                            {
                                hashMap.put("course", course);
                                hashMap.put("year", year);
                                hashMap.put("section", section);
                            }
                            else if (designation.equals("Faculty"))
                            {
                                hashMap.put("designation", designation);
                            }

                            hashMap.put("search", username.toLowerCase());

                            reference.collection("Users").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {

                                }
                            });


                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Illegal Email Id or Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
