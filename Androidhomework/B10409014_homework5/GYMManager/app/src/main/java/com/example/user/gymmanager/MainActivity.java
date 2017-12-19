package com.example.user.gymmanager;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.user.gymmanager.data.TasksContentProvider;

import java.util.Calendar;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton mfab;
    private Dialog mDlgAdd;
    private TimePicker tp;
    private RecyclerView mList;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Cursor c;
    private static ContentResolver mContRes;
    private AlarmManager alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContRes = getContentResolver();
        alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        mList = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);
        mList.setHasFixedSize(true);
        mfab = (FloatingActionButton) findViewById(R.id.fab);
        mfab.setOnClickListener(fabOnClick);
        c = getAllTasks();
        processControllers();
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mCallback);
        mItemTouchHelper.attachToRecyclerView(mList);
    }

    private View.OnClickListener fabOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mDlgAdd = new Dialog(MainActivity.this, R.style.ThemeOverlay_AppCompat_Dialog);
            mDlgAdd.setContentView(R.layout.mydlg);
            Button addbtnCancel = (Button) mDlgAdd.findViewById(R.id.btn_cancel);
            Button addbtnOk = (Button) mDlgAdd.findViewById(R.id.btn_ok);
            addbtnCancel.setOnClickListener(addbtnCancelOnClick);
            addbtnOk.setOnClickListener(addbtnOkOnClick);
            tp = (TimePicker) mDlgAdd.findViewById(R.id.timePicker);
            tp.setCurrentHour(8);
            tp.setCurrentMinute(0);
            mDlgAdd.show();
        }
    };

    private View.OnClickListener addbtnOkOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            try {
                EditText edittitle = (EditText) mDlgAdd.findViewById(R.id.editTask);
                String Title = edittitle.getText().toString();
                if (Title.equals(""))
                    throw new Exception();
                String Hour = new String();
                String Minute = new String();
                if (tp.getCurrentHour() < 10)
                    Hour = "0" + String.valueOf(tp.getCurrentHour());
                else
                    Hour = String.valueOf(tp.getCurrentHour());
                if (tp.getCurrentMinute() < 10)
                    Minute = "0" + String.valueOf(tp.getCurrentMinute());
                else
                    Minute = String.valueOf(tp.getCurrentMinute());

                ContentValues newRow = new ContentValues();
                newRow.put("task", Title);
                newRow.put("time", Hour + ":" + Minute);
                newRow.put("status", 1);

                Uri uri = mContRes.insert(TasksContentProvider.CONTENT_URI, newRow);
                adapter.swapCursor(getAllTasks());

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, tp.getCurrentHour());
                calendar.set(Calendar.MINUTE, tp.getCurrentMinute());
                calendar.set(Calendar.SECOND, 0);

                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                int taskId = (int) ContentUris.parseId(uri);
                Log.d("taskid", taskId + "");
                PendingIntent pending = PendingIntent.getBroadcast(MainActivity.this, taskId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                if (calendar.getTimeInMillis() > System.currentTimeMillis()) {
                    alarm.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pending);
                } else {
                    calendar.setTimeInMillis(calendar.getTimeInMillis() + 1000 * 60 * 60 * 24);
                    alarm.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pending);
                }
                makeToast(System.currentTimeMillis(), calendar.getTimeInMillis());
                mDlgAdd.cancel();
            } catch (Exception e) {
                Toast.makeText(v.getContext(), "Information is not complete !", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener addbtnCancelOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mDlgAdd.cancel();
        }
    };

    private Cursor getAllTasks() {
        return mContRes.query(TasksContentProvider.CONTENT_URI, null, null, null, "time");
    }

    ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mContRes.delete(TasksContentProvider.CONTENT_URI, "_id=" + viewHolder.itemView.getTag(), null);
            adapter.swapCursor(getAllTasks());

            Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
            PendingIntent pending = PendingIntent.getBroadcast(MainActivity.this, (int) viewHolder.itemView.getTag(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Log.d("tagid", viewHolder.itemView.getTag().toString());
            alarm.cancel(pending);
            pending.cancel();
        }
    };

    public void processControllers() {
        adapter = new Adapter(this, c) {
            @Override
            public void onBindViewHolder(final ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.mcheckbox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ContentValues mUpdateValues = new ContentValues();
                        mUpdateValues.put("task", holder.mTaskTxt.getText().toString());
                        mUpdateValues.put("time", holder.mTimeTxt.getText().toString());

                        String time = holder.mTimeTxt.getText().toString();
                        StringTokenizer tokens = new StringTokenizer(time, ":");
                        int hour = Integer.parseInt(tokens.nextToken().toString());
                        int min = Integer.parseInt(tokens.nextToken().toString());

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, min);
                        calendar.set(Calendar.SECOND, 0);

                        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                        PendingIntent pending = PendingIntent.getBroadcast(MainActivity.this, (int) holder.itemView.getTag(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        Log.d("tagid", holder.itemView.getTag().toString());

                        if (isChecked) {
                            mUpdateValues.put("status", "1");
                            if (calendar.getTimeInMillis() > System.currentTimeMillis()) {
                                alarm.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pending);
                            } else {
                                calendar.setTimeInMillis(calendar.getTimeInMillis() + 1000 * 60 * 60 * 24);
                                alarm.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pending);
                            }
                            makeToast(System.currentTimeMillis(), calendar.getTimeInMillis());
                        } else {
                            mUpdateValues.put("status", "0");
                            alarm.cancel(pending);
                        }
                        mContRes.update(TasksContentProvider.CONTENT_URI, mUpdateValues, "_id=" + holder.itemView.getTag(), null);
                    }
                });
            }
        };
        mList.setAdapter(adapter);
    }

    public void makeToast(Long systemtime, Long calendertime) {
        Toast.makeText(MainActivity.this,
                "Task will be triggered after \n  " +
                        (calendertime - systemtime) / (1000 * 60 * 60) +
                        " hours and " +
                        (calendertime - systemtime) % (1000 * 60 * 60) / (1000 * 60) +
                        " minutes ", Toast.LENGTH_SHORT).show();
    }

}
