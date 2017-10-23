package com.example.testvid.View;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testvid.Other.InternetCheck;
import com.example.testvid.Other.Preferences;
import com.example.testvid.Presenter.Adapters.PagerAdapter;
import com.example.testvid.R;
import com.example.testvid.Other.Variables;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private ImageButton ibLogout;
    private PagerAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Preferences.init(this);
        if (Preferences.getToken() == null) {
            Variables.isLogin = false;
        } else {
            Variables.isLogin = true;
        }
        checkConnection();
        setUI();
    }

    private void checkConnection() {
        if (!InternetCheck.isConnected(this))
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
    }

    private void setUI() {

        ibLogout = (ImageButton) findViewById(R.id.ibLogout);
        ibLogout.setVisibility(!Variables.isLogin ? View.GONE : View.VISIBLE);
        ibLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.feautured));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.$new));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.feed));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        tabAdapter = new PagerAdapter(getFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(tabAdapter);
        viewPager.setOffscreenPageLimit(0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void refreshPagerAdapter() {
        tabAdapter.notifyDataSetChanged();
        ibLogout.setVisibility(!Variables.isLogin ? View.GONE : View.VISIBLE);
    }

    private void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_logout);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Variables.isLogin = false;
                ibLogout.setVisibility(View.GONE);
                Preferences.setToken(null);
                refreshPagerAdapter();
                return true;
            }
        });
        popupMenu.show();
    }

    public void setVisibleProgressBar(Boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
