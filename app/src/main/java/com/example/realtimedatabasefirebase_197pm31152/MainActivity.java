package com.example.realtimedatabasefirebase_197pm31152;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ListView contract;
    ArrayAdapter<String> adapter;
   // String TAG = "FILEBASE";
    String databaseLink = "https://realtimedatabasefirebase-27cfd-default-rtdb.asia-southeast1.firebasedatabase.app";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contract = (ListView) findViewById(R.id.lv_contract);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        contract.setAdapter(adapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance(databaseLink);
        DatabaseReference myRef = database.getReference("contacts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                for (DataSnapshot data : snapshot.getChildren()
                ) {
                    String key = data.getKey();
                    String value = data.getValue().toString();
                    adapter.add(key + "\n" + value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Firebase ", "LoadPost: onCancelled", error.toException());
            }
        });
        contract.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String data = adapter.getItem(position);
                String key = data.split("\n")[0];
                Intent intent = new Intent(MainActivity.this, CapNhatContactActivity.class);
                intent.putExtra("id", key);
                startActivity(intent);
            }
        });
        contract.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String data = adapter.getItem(position);
                    String key = data.split("\n")[0];
                    Intent intent = new Intent(MainActivity.this, CapNhatContactActivity.class);
                    intent.putExtra("id", key);
                    startActivity(intent);
                return false;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuAdd) {
            //lay id lon nhat
            String data = adapter.getItem(adapter.getCount()-1);
            String contactId = data.split("\n")[0];
            int i_id = Integer.parseInt(contactId)+1;
            Intent intent = new Intent(MainActivity.this, ThemContactActivity.class);
            intent.putExtra("id", i_id);
            startActivity(intent);
        }
         else if (item.getItemId() == R.id.mnuIntro) {
                Toast.makeText(this, "WELCOME", Toast.LENGTH_LONG).show();
            }
         else if(item.getItemId() ==R.id.mnuSignup){
             Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
             startActivity(intent);
        }
         else if(item.getItemId() ==R.id.mnuSignIn){
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        }


            return super.onOptionsItemSelected(item);
        }
    }
