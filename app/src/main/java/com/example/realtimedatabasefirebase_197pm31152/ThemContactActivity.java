package com.example.realtimedatabasefirebase_197pm31152;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThemContactActivity extends AppCompatActivity {
EditText id, ten, gt, dc, email, sdt;
Button them, huy;
Integer i_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_contact);
        matching();
        Intent intent = getIntent();
        i_id = intent.getIntExtra("id",-1);
        id.setEnabled(false);
        id.setText(String.valueOf(i_id));

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyThemMoi();
            }

            private void xulyThemMoi() {
                try{
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("contacts");

                    String contactId = id.getText().toString().trim();
                    String sname = ten.getText().toString().trim();
                    String sgender = gt.getText().toString().trim();
                    String saddress = dc.getText().toString().trim();
                    String semail = email.getText().toString().trim();
                    String sphone = sdt.getText().toString().trim();

                    myRef.child(contactId).child("id").setValue(contactId);
                    myRef.child(contactId).child("name").setValue(sname);
                    myRef.child(contactId).child("gender").setValue(sgender);
                    myRef.child(contactId).child("address").setValue(saddress);
                    myRef.child(contactId).child("email").setValue(semail);
                    myRef.child(contactId).child("phone").setValue(sphone);

                    Toast.makeText(getApplicationContext(), "Thêm thành công" + contactId, Toast.LENGTH_LONG).show();

                    finish();
                }catch (Exception e){
                    Log.d("Error", e.toString());
                }
            }
        });
    }

    private void matching() {
        id = (EditText) findViewById(R.id.et_id);
        ten = (EditText) findViewById(R.id.et_name);
        gt = (EditText) findViewById(R.id.et_gender);
        dc = (EditText) findViewById(R.id.et_address);
        email = (EditText) findViewById(R.id.et_email);
        sdt = (EditText) findViewById(R.id.et_phone);
        them = (Button) findViewById(R.id.btn_Up);
        huy = (Button) findViewById(R.id.btn_Cancel);
    }
}