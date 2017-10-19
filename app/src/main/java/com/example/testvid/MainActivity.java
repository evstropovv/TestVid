package com.example.testvid;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.testvid.Adapters.PagerAdapter;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
    }

    private void setUI() {

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Feautured"));
        tabLayout.addTab(tabLayout.newTab().setText("New"));
        tabLayout.addTab(tabLayout.newTab().setText("Feed"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter tabAdapter = new PagerAdapter(getFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition()); }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }
}
