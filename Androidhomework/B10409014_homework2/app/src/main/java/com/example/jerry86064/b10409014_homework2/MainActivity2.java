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
import android.widget.TextView;

/**
 * Created by jerry86064 on 2017/03/21.
 */

public class MainActivity2 extends  MainActivity {

    @Override//建立menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClickedId = item.getItemId();            //check item id
        if (itemClickedId == R.id.ReturnActivity1) {
            Intent intent = new Intent();
            intent.setClass(MainActivity2.this , MainActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    private TextView DisplayText;
    private Button   click2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DisplayText=(TextView) findViewById(R.id.textView2);
        click2=(Button) findViewById(R.id.button2);


        click2.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick (View v){

                Context context = MainActivity2.this;
                Class destinationActivity = MainActivity.class;
                Intent startMainActivity1Intent = new Intent(context, destinationActivity);
                startActivity(startMainActivity1Intent);
            }
        });
    }
}
