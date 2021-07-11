package com.example.mobil_alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Extra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        TextView btn4= findViewById(R.id.btnabtdvlpr);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Extra.this,AboutDeveloper.class));
            }
        });
        TextView btn2= findViewById(R.id.btnriddle);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Extra.this,Riddle.class));
            }
        });
        TextView btn1= findViewById(R.id.btngame);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Extra.this,Games.class));
            }
        });
        TextView btn3= findViewById(R.id.btncontactus);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Extra.this,ContactUs.class));
            }
        });
    }
}