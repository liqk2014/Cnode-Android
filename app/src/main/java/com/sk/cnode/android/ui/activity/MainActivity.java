package com.sk.cnode.android.ui.activity;


import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sk.cnode.android.ui.fragment.TopicFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements TopicFragment.OnFragmentInteractionListener {

    // 抽屉导航布局
    @Bind(com.sk.cnode.android.R.id.main_drawer_layout)
    protected DrawerLayout drawerLayout;


    private ActionBarDrawerToggle mDrawerToggle;

    // actionBar
    @Bind(com.sk.cnode.android.R.id.main_toolbar)
    protected Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.sk.cnode.android.R.layout.activity_main);
        ButterKnife.bind(this);


        initView();


    }

    private void initView() {


        initToolBar();

        initFragment();

        initRecyleView();

    }


    private void initToolBar() {
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        toolbar.setLogo(com.sk.cnode.android.R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//返回键是否显示
//        getSupportActionBar().setHomeButtonEnabled(true);//应用图标是否可以点击,此api在toolbar中好像没有效果，待验证
//        getSupportActionBar().setDisplayShowHomeEnabled(true);//应用图标是否显示
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, com.sk.cnode.android.R.string.open, com.sk.cnode.android.R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                mAnimationDrawable.stop();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                mAnimationDrawable.start();
            }
        };


        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);

        drawerLayout.setDrawerShadow(com.sk.cnode.android.R.drawable.navigation_drawer_shadow, GravityCompat.START);
//        drawerLayout.setDrawerListener(openDrawerListener);


    }

    private void initFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment topicFragment = new TopicFragment();
        transaction.replace(com.sk.cnode.android.R.id.main_content, topicFragment);
        transaction.commit();


    }

    private void initRecyleView(){



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.sk.cnode.android.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.sk.cnode.android.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private DrawerLayout.DrawerListener openDrawerListener = new DrawerLayout.SimpleDrawerListener() {

        @Override
        public void onDrawerOpened(View drawerView) {
//            updateUserInfoViews();
//            getUserAsyncTask();
//            getMessageCountAsyncTask();
        }

    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
