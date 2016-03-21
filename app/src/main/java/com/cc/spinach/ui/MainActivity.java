package com.cc.spinach.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


import com.cc.spinach.R;
import com.cc.spinach.base.BaseActivity;
import com.cc.spinach.fragment.NewsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.navigationView)
    NavigationView mNavigationView;
    @Bind(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        setContent(R.id.drawer_tab1);

    }

    private void initView() {
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open ,R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                setContent(menuItem.getItemId());
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }


    private void setContent(int id){
        switch (id){
            case R.id.drawer_tab1:
                Log.d("cc","主题");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new NewsFragment()).commit();
                mToolbar.setTitle("");
                break;
            case R.id.drawer_tab2:
                Log.d("cc","节点");


                break;
            case R.id.drawer_about:
                Log.d("cc","关于");

                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
