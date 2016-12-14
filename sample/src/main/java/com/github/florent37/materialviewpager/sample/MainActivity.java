package com.github.florent37.materialviewpager.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.github.florent37.materialviewpager.sample.fragment.PhotoFragment;
import com.github.florent37.materialviewpager.sample.fragment.RecyclerViewFragment;
import com.github.florent37.materialviewpager.sample.fragment.WebViewFragment;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends DrawerActivity {

    private MaterialViewPager mViewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:
                        return RecyclerViewFragment.newInstance();
//                        return PhotoFragment.newInstance();
                    case 1:
//                        return RecyclerViewFragment.newInstance();
                        return PhotoFragment.newInstance();
                    case 2:
                        return WebViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Tab 1";
                    case 1:
                        return "Flickr Photos";
                    case 2:
                        return "Tab 3";
                    case 3:
                        return "Tab 4";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.green,
                            "http://www.freemagebank.com/wp-content/uploads/edd/2015/10/GL0000415LR.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.blue,
                            "http://www.freemagebank.com/wp-content/uploads/edd/2015/09/GL0000383LR.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.cyan,
                            "http://kaboompics.com/files/upload/o_1b28pv05a1bid1sh11jdta3g13ka7_new.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.red,
                            "http://cdn.barnimages.com/wp-content/uploads/2016/11/2016-03-01-roman-drits-barnimages-007-768x512@2x.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
