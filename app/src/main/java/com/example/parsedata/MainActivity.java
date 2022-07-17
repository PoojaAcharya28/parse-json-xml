package com.example.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button jsonParseBtn, xmlParsebtn;

       jsonParseBtn =(Button) findViewById(R.id.jsonParseBtn);
       xmlParsebtn = (Button) findViewById(R.id.xmlParseBtn);

       jsonParseBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               System.out.println("--------------jsonParseBtn is pressed------------");
               Intent intent = new Intent(MainActivity.this, ViewParsedData.class);
               intent.putExtra("mode", 1);
               startActivity(intent);
           }
       });

        xmlParsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewParsedData.class);
                intent.putExtra("mode",2);
                startActivity(intent);
            }
        });

    }
}