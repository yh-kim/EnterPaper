package com.enterpaper.menu.myinfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.enterpaper.R;
import com.enterpaper.util.SetFont;

/**
 * Created by Kim on 2015-08-08.
 */
public class MyInfoActivity extends Activity {
    ImageView imgBack;
    TextView myinfo_user_name,myinfo_birthday,myinfo_sex,myinfo_email,myinfo_phone,myinfo_nationality;


    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        myinfo_user_name = (TextView)findViewById(R.id.myinfo_user_name);
        myinfo_birthday = (TextView)findViewById(R.id.myinfo_birthday);
        myinfo_sex = (TextView)findViewById(R.id.myinfo_sex);
        myinfo_email = (TextView)findViewById(R.id.myinfo_email);
        myinfo_phone = (TextView)findViewById(R.id.myinfo_phone);
        myinfo_nationality = (TextView)findViewById(R.id.myinfo_nationality);



        imgBack = (ImageView)findViewById(R.id.myinfo_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //TextView 폰트 지정
        SetFont.setGlobalFont(this, getWindow().getDecorView());

    }

    //onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
