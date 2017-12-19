package com.example.jerry86064.b10409014_homework4;

/**
 * Created by jerry86064 on 2017/05/07.
 */

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by jerryold on 2017/5/1.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.SessViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    public Myadapter(Context context ,Cursor cursor) {
        this.mCursor = cursor;
        this.mContext = context;
    }

    @Override
    public Myadapter.SessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.memberitem , parent,false);
        return new SessViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SessViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        String guestName = mCursor.getString(mCursor.getColumnIndex(Db_contract.ManagerEntry.COLUMN_GUEST_NAME));
        int guestAge = mCursor.getInt(mCursor.getColumnIndex(Db_contract.ManagerEntry.COLUMN_GUEST_AGE));
        int guestGender = mCursor.getInt(mCursor.getColumnIndex(Db_contract.ManagerEntry.COLUMN_GUEST_GENDER));
        int id = mCursor.getInt(mCursor.getColumnIndex(Db_contract.ManagerEntry._ID));
        holder.idtextView.setText(Integer.valueOf(id).toString());
        holder.guestNameTextView.setText(guestName);
        holder.ageTextView.setText(Integer.valueOf(guestAge).toString());
        holder.itemView.setTag(id);
        if(guestGender==1){
            holder.genderImg.setImageResource(R.mipmap.female_icon);
        }
        else {
            holder.genderImg.setImageResource(R.mipmap.male_icon_2);
            System.out.println("gender result check " +guestGender);
        }
    }
    public void swapCursor(Cursor cursor){
        if(mCursor!=null){
            mCursor.close();
        }
        mCursor = cursor;
        if(cursor!=null){
            this.notifyDataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    class SessViewHolder extends RecyclerView.ViewHolder{

        TextView idtextView;
        TextView guestNameTextView;
        TextView ageTextView;
        ImageView genderImg;
        public SessViewHolder(View itemView) {
            super(itemView);
            idtextView = (TextView)itemView.findViewById(R.id.id_text_view);
            guestNameTextView = (TextView)itemView.findViewById(R.id.guest_name_text_view);
            ageTextView = (TextView)itemView.findViewById(R.id.age_text_view);
            genderImg = (ImageView) itemView.findViewById(R.id.gender_img);
        }
    }
}
