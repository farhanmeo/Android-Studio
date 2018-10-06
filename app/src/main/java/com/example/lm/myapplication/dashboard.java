package com.example.lm.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//Variables Declaration
        final ArrayList<String> arrayList = new ArrayList<>();
        final Button add = (Button) findViewById(R.id.b1);
        final ListView list= (ListView) findViewById(R.id.list1);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        final EditText cat = (EditText)findViewById(R.id.category);

        try {

            PrintStream out = new PrintStream(openFileOutput("category.txt", MODE_APPEND));
            //out.println("Welcome To MSE");

            final Scanner scanner =  new Scanner(openFileInput("category.txt"));
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                arrayList.add(line.toString());
                arrayAdapter.notifyDataSetChanged();
//                Toast.makeText(this, line, Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex)
        {

        }



        list.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrintStream out = null;
                try {
                    out = new PrintStream(openFileOutput("category.txt", MODE_APPEND));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                out.println(cat.getText().toString());
                arrayList.add(cat.getText().toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}
