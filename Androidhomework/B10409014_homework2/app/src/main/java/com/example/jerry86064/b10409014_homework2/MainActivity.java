package com.example.jerry86064.b10409014_homework2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {




    @Override//建立menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override //MENU的跳躍
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClickedId = item.getItemId();            //check item id
        if (itemClickedId == R.id.GoActivity2) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this , MainActivity2.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private EditText textdesign1;
    private Button click1;
    @Override//BUTTON的跳躍
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        click1=(Button)  findViewById(R.id.button1);
        textdesign1 =(EditText)findViewById(R.id.text1);

        click1.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick (View v){

                Context context = MainActivity.this;
                Class destinationActivity = MainActivity2.class;
                Intent startMainActivity2Intent = new Intent(context, destinationActivity);
                startActivity(startMainActivity2Intent);
            }
        });
    }
}





