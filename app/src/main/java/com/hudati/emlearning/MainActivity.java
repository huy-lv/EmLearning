package com.hudati.emlearning;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.APIInterface;
import com.hudati.emlearning.api.HomeResponse;
import com.hudati.emlearning.api.RootApiResponse;
import com.hudati.emlearning.fragment.HomeFragment;
import com.hudati.emlearning.fragment.MiddleFragment;
import com.hudati.emlearning.fragment.UserFragment;
import com.hudati.emlearning.model.Banner;
import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.model.Category;
import com.hudati.emlearning.model.Practice;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hudati.emlearning.util.Utils.LOGGED_IN;
import static com.hudati.emlearning.util.Utils.isNetworkConnected;
import static com.hudati.emlearning.util.Utils.showInfoDialog;

public class MainActivity extends AppCompatActivity {


    public ArrayList<Book> bookList;
    public ArrayList<Practice> practice_list;
    public ArrayList<Category> categories;
    public ArrayList<Banner> bannerList;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.main_activity_pb)
    ProgressBar main_activity_pb;
    @BindView(R.id.main_activity_retry)
    TextView main_activity_retry;
    @BindView(R.id.main_activity_fl)
    FrameLayout main_activity_fl;
    HomeFragment homeFragment;
    MiddleFragment middleFragment;
    UserFragment userFragment;
    APIInterface apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        categories = new ArrayList<>();
        bookList = new ArrayList<>();
        practice_list = new ArrayList<>();
        bannerList = new ArrayList<>();

        checkLogin();
        //loadDataForFragment
        apiService = APIClient.getClient().create(APIInterface.class);
        if (Utils.isNetworkConnected(this)) {
            loadRootApi();
        } else {
            main_activity_pb.setVisibility(View.INVISIBLE);
            Utils.showInfoDialog(this, getResources().getString(R.string.no_internet));
        }
    }

    private void checkLogin() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Utils.LOGGED_IN = sp.getBoolean(Utils.SP_CHECK_USER, false);
        if (LOGGED_IN) Utils.ACCESS_TOKEN = sp.getString(Utils.SP_ACCESS_TOKEN, null);
    }

    void loadRootApi() {
        main_activity_pb.setVisibility(View.VISIBLE);
        Call<RootApiResponse> call = apiService.loadApiList();
        call.enqueue(new Callback<RootApiResponse>() {
            @Override
            public void onResponse(Call<RootApiResponse> call, Response<RootApiResponse> response) {
                if (response.isSuccessful()) {
                    main_activity_retry.setVisibility(View.INVISIBLE);
                    saveApi(response.body().getApiList());
                    loadHomePage(response.body().getApiList().get_api_home());
                } else {
                    main_activity_pb.setVisibility(View.INVISIBLE);
                    showInfoDialog(MainActivity.this, "Server error:" + response.message());
                }
            }

            @Override
            public void onFailure(Call<RootApiResponse> call, Throwable t) {
                showInfoDialog(MainActivity.this, "Load api root error!\n" + t.getMessage());
                main_activity_pb.setVisibility(View.INVISIBLE);
            }
        });
    }

    @OnClick(R.id.main_activity_fl)
    void reload() {
        if (isNetworkConnected(this)) loadRootApi();
        else {
            Utils.showInfoDialog(this, getResources().getString(R.string.no_internet));
        }
    }

    private void saveApi(RootApiResponse.APIList apiList) {
        Utils.apiList = apiList;
    }

    private void loadHomePage(String api) {
        Call<HomeResponse> call = APIClient.getInterface().loadHomePageFromApi(api);
        call.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if (response.isSuccessful()) {

                    //fragments
                    homeFragment = new HomeFragment();
                    middleFragment = new MiddleFragment();
                    userFragment = new UserFragment();
                    setBanner(response.body().getData().getBanners());

                    setCategories(response.body().getData().getCategories());

                    setBookList(response.body().getData().getBooks());

                    setPracticeList(response.body().getData().getPractices());

                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
                    viewPagerAdapter.addFrag(homeFragment);
                    viewPagerAdapter.addFrag(middleFragment);
                    viewPagerAdapter.addFrag(userFragment);
                    viewpager.setAdapter(viewPagerAdapter);

                    tabs.setupWithViewPager(viewpager);
                    tabs.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
                    tabs.getTabAt(1).setIcon(R.drawable.ic_ac_unit_black_24dp);
                    tabs.getTabAt(2).setIcon(R.drawable.ic_person_black_24dp);

                    main_activity_pb.setVisibility(View.INVISIBLE);
                    viewpager.setVisibility(View.VISIBLE);
                } else {
                    showInfoDialog(MainActivity.this, response.message());

                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                showInfoDialog(MainActivity.this, t.getMessage());
            }
        });
    }


    public void setBanner(ArrayList<Banner> banners) {
        bannerList.clear();
        bannerList.addAll(banners);
    }

    public void setPracticeList(ArrayList<Practice> practices) {
//        practice_list_pb.setVisibility(View.GONE);
        practice_list.clear();
        practice_list.addAll(practices);
//        practiceAdapter.notifyDataSetChanged();
    }

    public void setBookList(ArrayList<Book> books) {
//        book_list_pb.setVisibility(View.GONE);
        bookList.clear();
        bookList.addAll(books);
//        bookAdapter.notifyDataSetChanged();
    }

    public void setCategories(ArrayList<Category> cs) {
//        main_layout_category_pb.setVisibility(View.GONE);
        categories.clear();
        categories.addAll(cs);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
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
