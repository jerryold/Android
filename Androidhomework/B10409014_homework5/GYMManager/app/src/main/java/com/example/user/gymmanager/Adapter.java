package com.example.user.gymmanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by B10409038 on 2017/5/27.
 */

public class Adapter extends
        RecyclerView.Adapter<Adapter.ViewHolder> {

    private Cursor mCursor;
    private Context mContext;
    private Intent intent = new Intent();

    public Adapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        intent.setClass(mContext, AlarmReceiver.class);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTaskTxt;
        public TextView mTimeTxt;
        public CheckBox mcheckbox;

        public ViewHolder(final View view) {
            super(view);
            mTaskTxt = (TextView) itemView.findViewById(R.id.txtTask);
            mTimeTxt = (TextView) itemView.findViewById(R.id.txtTime);
            mcheckbox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        holder.mTaskTxt.setText(mCursor.getString(mCursor.getColumnIndex("task")));
        holder.mTimeTxt.setText(mCursor.getString(mCursor.getColumnIndex("time")));
        int id = (int) mCursor.getLong(mCursor.getColumnIndex("_id"));
        holder.itemView.setTag(id);

        if (mCursor.getString(mCursor.getColumnIndex("status")).equals("1"))
            holder.mcheckbox.setChecked(true);
        else
            holder.mcheckbox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null)
            mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
