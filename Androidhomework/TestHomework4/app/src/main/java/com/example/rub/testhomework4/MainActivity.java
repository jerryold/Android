package com.example.rub.testhomework4;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    RadioButton maleRadiobutton ;
    RadioButton femaleRadiobutton;
    EditText mNameeditText;
    EditText mAgeeditText;
    RecyclerView mMemList;
    SessMemberAdapter memberAdapter;
    private String morderBy;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        morderBy = SessionManagerContract.SessionManagerEntry._ID;
        mMemList = (RecyclerView)findViewById(R.id.member_list_recy_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       SManagerDbHelper managerDbHelper = new SManagerDbHelper(this);
        mDb = managerDbHelper.getWritableDatabase();
        mMemList.setLayoutManager(new LinearLayoutManager(this));
        memberAdapter = new SessMemberAdapter(this,getAllMemList());
        mMemList.setAdapter(memberAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = (int) viewHolder.itemView.getTag();
                removeMember(id);
                memberAdapter.swapCursor(getAllMemList());
            }
        }).attachToRecyclerView(mMemList);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void addnew(View bn){
        LayoutInflater li = LayoutInflater.from(MainActivity.this);
        View v = li.inflate(R.layout.jumpform, null);

        AlertDialog.Builder alertdiaglogbuilder = new AlertDialog.Builder(MainActivity.this);
        alertdiaglogbuilder.setView(v);
        maleRadiobutton = (RadioButton) v.findViewById(R.id.male_radio_button);
        femaleRadiobutton = (RadioButton)v.findViewById(R.id.female_radio_button);
        mNameeditText = (EditText) v.findViewById(R.id.name_edit_text);
        mAgeeditText = (EditText) v.findViewById(R.id.Age_edit_text);
        alertdiaglogbuilder.setCancelable(true)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                //
                                if(mNameeditText.getText().length() ==0 || mAgeeditText.getText().length() ==0){
                                    return;
                                }
                                int gender;
                               gender = maleRadiobutton.isChecked() ? 0:1;
                                System.out.println("Input gender check :" +gender);
                                String guestName = mNameeditText.getText().toString();
                                int guestAge =1;
                                try {
                                    guestAge =  Integer.parseInt(mAgeeditText.getText().toString());
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                addnewMember(guestName , guestAge , gender);
                                memberAdapter.swapCursor(getAllMemList());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertdiaglogbuilder.create();
        // show it
        alertDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.item_sort_by_id:
                morderBy = SessionManagerContract.SessionManagerEntry._ID;
                break;
            case R.id.item_sort_by_name:
                morderBy = SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_NAME;
                break;
            case R.id.item_sort_by_gender:
                morderBy = SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_GENDER;
                break;
            case R.id.item_sort_by_age:
                morderBy = SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_AGE;
                break;
        }
        memberAdapter.swapCursor(getAllMemList());
        return super.onOptionsItemSelected(item);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male_radio_button:
                if (checked)
                    break;
            case R.id.female_radio_button:
                if (checked)
                    break;
        }
    }
    public long addnewMember(String memberName ,int memberAge , int memberGender){
        ContentValues cv = new ContentValues();
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_NAME , memberName);
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_AGE , memberAge);
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_GENDER , memberGender);
        return mDb.insert(SessionManagerContract.SessionManagerEntry.TABLE_NAME ,null,cv);
    }
    public Cursor getAllMemList(){
        return mDb.query(
                SessionManagerContract.SessionManagerEntry.TABLE_NAME,null ,null ,null,null,null,morderBy
        );
    }
    public int removeMember(int id){
        return mDb.delete(SessionManagerContract.SessionManagerEntry.TABLE_NAME , SessionManagerContract.SessionManagerEntry._ID + "=" + id , null);
    }
    public void genderButtonClick(View v){
        switch (v.getId()){
            case  R.id.male_icon:
                maleRadiobutton.setChecked(true);
                femaleRadiobutton.setChecked(false);
                break;
            case R.id.female_icon:
                maleRadiobutton.setChecked(false);
                femaleRadiobutton.setChecked(true);
                break;
        }
    }

}
