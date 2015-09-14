package com.enterpaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.enterpaper.menu.MenuAdapter;
import com.enterpaper.menu.MenuListItem;
import com.enterpaper.menu.mybooth.MyBoothActivity;
import com.enterpaper.menu.myinfo.MyInfoActivity;
import com.enterpaper.menu.notice.NoticeActivity;
import com.enterpaper.menu.setting.SettingActivity;
import com.enterpaper.util.BackPressCloseHandler;
import com.enterpaper.util.SetFont;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private BackPressCloseHandler backPressCloseHandler;
    Toolbar mToolBar;
    DrawerLayout mDrawerLayout;
    ImageView drawerImageView,drawerclose;
    ArrayList<MenuListItem> dataList;
    ListView menu_list,main_list;
    MenuAdapter adapters;
    //Adapter 생성
    ContentAdapter adapter;
    //ArrayList 생성
    ArrayList<ContentItem> arr_list = new ArrayList<ContentItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        initMainList();

        //취소버튼 눌렀을 때 핸들러
        backPressCloseHandler = new BackPressCloseHandler(this);

        //TextView 폰트 지정
        SetFont.setGlobalFont(this, getWindow().getDecorView());

        //Toolbar 생성
        initToolbar();

        //Drawer 생성
        initNaviDrawer();

        //menu list
        initMenuList();
    }

    private void initMainList(){
        main_list = (ListView)findViewById(R.id.lv_main);

        arr_list.add(new ContentItem());
        arr_list.add(new ContentItem());
        arr_list.add(new ContentItem());
        arr_list.add(new ContentItem());

        //Adapter 생성
        adapter = new ContentAdapter(getApplicationContext(), R.layout.row_content, arr_list);

        //Adapter와 GirdView를 연결
        main_list.setAdapter(adapter);

        adapter.notifyDataSetChanged();



        main_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    private void initToolbar(){
        //액션바 객체 생성
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //액션바 설정
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        //액션바 숨김
        actionBar.hide();

        //툴바 설정
        mToolBar = (Toolbar) findViewById(R.id.main_toolbar);
        mToolBar.setContentInsetsAbsolute(0, 0);
    }

    private void initNaviDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //메뉴버튼
        drawerImageView = (ImageView) findViewById(R.id.drawer_imageview);

        drawerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //닫기버튼
        drawerclose = (ImageView) findViewById(R.id.menu_close);
        drawerclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    //취소버튼 눌렀을 때
    @Override
    public void onBackPressed() {
        //drawer가 열려있으면
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }

        //핸들러 작동
        backPressCloseHandler.onBackPressed();
    }

    private void initMenuList(){
        addItemsMenu();

        menu_list = (ListView)findViewById(R.id.menu_list);

        //Adapter 생성
        adapters = new MenuAdapter(this, R.layout.row_menu, dataList);

        //Adapter와 GirdView를 연결
        menu_list.setAdapter(adapters);


        adapters.notifyDataSetChanged();

        //이미지 터치 못하게
        ImageView imageView99 = (ImageView)findViewById(R.id.imageView99);
        imageView99.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        //menu button 눌렀을 때
        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent myInfo = new Intent(getApplicationContext(), MyInfoActivity.class);
                        startActivity(myInfo);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case 1:
                        Intent myBooth = new Intent(getApplicationContext(), MyBoothActivity.class);
                        startActivity(myBooth);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case 2:
                        Intent notice = new Intent(getApplicationContext(), NoticeActivity.class);
                        startActivity(notice);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case 3:
                        Intent setting = new Intent(getApplicationContext(), SettingActivity.class);
                        startActivity(setting);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                }
            }
        });
    }

    private void addItemsMenu(){
        dataList = new ArrayList<>();

        dataList.add(new MenuListItem(R.drawable.menu_my,"내 정보"));
        dataList.add(new MenuListItem(R.drawable.menu_hart,"내 리스트"));
        dataList.add(new MenuListItem(R.drawable.menu_alarm,"알림"));
        dataList.add(new MenuListItem(R.drawable.menu_setting, "설정"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
