package com.hudati.emlearning;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hudati.emlearning.fragment.HomeFragment;
import com.hudati.emlearning.fragment.MiddleFragment;
import com.hudati.emlearning.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    HomeFragment homeFragment;
    MiddleFragment middleFragment;
    UserFragment userFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //fragments
        homeFragment = new HomeFragment();
        middleFragment = new MiddleFragment();
        userFragment = new UserFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPagerAdapter.addFrag(homeFragment);
        viewPagerAdapter.addFrag(middleFragment);
        viewPagerAdapter.addFrag(userFragment);
        viewpager.setAdapter(viewPagerAdapter);

        tabs.setupWithViewPager(viewpager);
        tabs.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabs.getTabAt(1).setIcon(R.drawable.ic_ac_unit_black_24dp);
        tabs.getTabAt(2).setIcon(R.drawable.ic_person_black_24dp);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
