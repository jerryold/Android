/*
 *Reference:Udacity ud851-T03.07-Solution-RecyclerViewClickHandling
 *Created by wahaha on 09/04/2017.
 *Copyright 2017 wahaha. All rights reserved.Created by wahaha on 09/04/2017.
 */
package com.example.android.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.ViewHolder> {
    private int NumItems;
    private int min, max, answer;
    private int[] position;
    static int counter=0;

    public GreenAdapter(int quantity) {
        NumItems = quantity;
        min = 0;
        max = quantity - 1;
        answer = (int) (Math.random() * quantity);
       position = new int[NumItems];
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Txt;
        public View rootView;


        public ViewHolder(View view) {
            super(view);
            Txt = (TextView) itemView.findViewById(R.id.tv_item_number);

            rootView = view;
        }
    }

    @Override
    public GreenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.number_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int state) {
        holder.Txt.setText(String.valueOf(state + 1));
        switch (position[state])
        {
            case 0:holder.itemView.setBackgroundColor(Color.WHITE);
                break;
            case 1:holder.itemView.setBackgroundColor(Color.GRAY);
                break;
            case 2:holder.itemView.setBackgroundColor(Color.RED);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return NumItems;
    }

    public boolean game(int choose) {

                    if (choose > answer) {
                        for (int i = choose; i <= max; i++) {
                            position[i] = 1;
                        }
                        max = choose;
                        counter++;
                    }
                    else if (choose < answer) {
                        for (int i = min; i <= choose; i++) {
                            position[i] = 1;
                        }
                        min = choose;
                        counter++;
                    }
                    else if (choose == answer) {
                        position[choose] = 2;
                        return true;
                    }
                    return false;
    }

    public String getanswer() {
        String toast = "本回合結束,答案為 " + (answer + 1) + "你總共猜了"+(counter)+"次";


        return toast;
    }
}