package com.example.bottomnavigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.bottomnavigationdrawer.Adapter.ViewPagerAdapter;
import com.example.bottomnavigationdrawer.Fragment.HomeFragment;
import com.example.bottomnavigationdrawer.Fragment.InfoFragment;
import com.example.bottomnavigationdrawer.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
private BottomNavigationView bottomNavigationView;
private ViewPager viewPager;
private MenuItem prevItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.search:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.info:
                        viewPager.setCurrentItem(2);
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
if(prevItem!=null){
    prevItem.setChecked(false);
}else{
    bottomNavigationView.getMenu().getItem(0).setChecked(false);
}
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setUpPageViewer(viewPager);
    }
    private void setUpPageViewer(ViewPager viewPager){
        ViewPagerAdapter mAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new HomeFragment());
        mAdapter.addFragment(new SearchFragment());
        mAdapter.addFragment(new InfoFragment());
        viewPager.setAdapter(mAdapter);

    }
}