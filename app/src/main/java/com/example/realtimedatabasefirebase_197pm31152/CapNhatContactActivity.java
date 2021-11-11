package com.example.realtimedatabasefirebase_197pm31152;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CapNhatContactActivity extends AppCompatActivity {
    EditText id, ten, gt, dc, email, sdt;
    Button xoa, huy, capnhat;
    String i_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_contact);
        matching();
        getContactDetail();
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                    Toast.makeText(getApplicationContext(), "Cập nhật thành công " + contactId, Toast.LENGTH_LONG).show();

                    finish();
                }catch (Exception e){
                    Log.d("Error Update", e.toString());
                }
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("contacts");
                myRef.child(String.valueOf(i_id)).removeValue();
                finish();
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getContactDetail() {
        Intent intent = getIntent();
        i_id = intent.getStringExtra("id");
        id.setEnabled(false);
        id.setText(String.valueOf(i_id));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myReference = database.getReference("contacts");
        myReference.child(i_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    HashMap<String, Object> hashMap = (HashMap<String, Object>) snapshot.getValue();
                    ten.setText(hashMap.get("name").toString());
                    gt.setText(hashMap.get("gender").toString());
                    dc.setText(hashMap.get("address").toString());
                    email.setText(hashMap.get("email").toString());
                    sdt.setText(hashMap.get("phone").toString());
                }catch (Exception e){
                    Log.d( "LOI_JSON ",e.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d( "LOI_CHITIET ","LoadPost:onCancel",error.toException());
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
        xoa = (Button) findViewById(R.id.btn_delete);
        huy = (Button) findViewById(R.id.btn_Cancel);
        capnhat = (Button) findViewById(R.id.btn_Up);

    }
}