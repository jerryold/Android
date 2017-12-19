/*
 *Reference:Udacity ud851-T03.07-Solution-RecyclerViewClickHandling
 *Created by wahaha on 09/04/2017.
 *Copyright 2017 wahaha. All rights reserved.Created by wahaha on 09/04/2017.
 */
package com.example.android.recyclerview;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import android.view.Menu;
//import android.view.MenuItem;
//import android.content.Intent;
public class MainActivity extends AppCompatActivity {
    private int NUM_LIST_ITEMS;
    private EditText EdtTxt;
    private RecyclerView mNumbersList;
    private GreenAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "開始新的回合!", Toast.LENGTH_SHORT).show();
        EdtTxt = (EditText) findViewById(R.id.et_1);
        NUM_LIST_ITEMS = Integer.parseInt(EdtTxt.getText().toString());
        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);
        layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);
        Controllers();
        Controllers1();


    }

    public void Return(){
        mAdapter = new GreenAdapter(NUM_LIST_ITEMS);
        mNumbersList.setAdapter(mAdapter);
        Control();
    }

    public void Control() {
        mAdapter = new GreenAdapter(NUM_LIST_ITEMS) {
            @Override
            public void onBindViewHolder(final ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mAdapter.game(position))
                            Toast.makeText(view.getContext().getApplicationContext(), mAdapter.getanswer(), Toast.LENGTH_LONG).show();
                        mNumbersList.setAdapter(mAdapter);
//
                    }

                });
            }
        };
        mNumbersList.setAdapter(mAdapter);
    }
    public void Controllers() {
/*重製按鈕*/
       mNumbersList.setAdapter(mAdapter);

        Return();
        Button bt= (Button) findViewById(R.id.bt_1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.et_1);
                String input = et.getText().toString();
                NUM_LIST_ITEMS = Integer.valueOf(input);
//
               Return();
                Toast.makeText(getApplicationContext(), "開始新的的回合", Toast.LENGTH_SHORT).show();
//
            }


        });
    };

    public void Controllers1() {
/*設定數字範圍*/
        mNumbersList.setAdapter(mAdapter);


        Button bt= (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.et_1);
                String input = et.getText().toString();
                NUM_LIST_ITEMS = Integer.valueOf(input);
//
                Return();
                Toast.makeText(getApplicationContext(), "設定數字範圍!", Toast.LENGTH_SHORT).show();
//
            }


        });
    };

}