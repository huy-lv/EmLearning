package com.hudati.emlearning.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.RotateDownTransformer;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.hudati.emlearning.R;
import com.hudati.emlearning.activity.BookListActivity;
import com.hudati.emlearning.activity.CategoryActivity;
import com.hudati.emlearning.activity.PracticeListActivity;
import com.hudati.emlearning.adapter.BookAdapter;
import com.hudati.emlearning.adapter.PracticeAdapter;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.APIInterface;
import com.hudati.emlearning.api.HomeResponse;
import com.hudati.emlearning.api.RootApiResponse;
import com.hudati.emlearning.base.BaseFragment;
import com.hudati.emlearning.model.Banner;
import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.model.Category;
import com.hudati.emlearning.model.Practice;
import com.hudati.emlearning.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hudati.emlearning.util.Utils.INTENT_KEY_ACTION_LECTURE;
import static com.hudati.emlearning.util.Utils.INTENT_KEY_CATEGORY_NAME;
import static com.hudati.emlearning.util.Utils.showInfoDialog;

/**
 * Created by huylv on 22-Mar-17.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.book_list_rv)
    RecyclerView book_list_rv;
    BookAdapter bookAdapter;

    @BindView(R.id.main_layout_category)
    LinearLayout main_layout_category;
    ArrayList<Category> categories;
    @BindView(R.id.main_layout_category_pb)
    ProgressBar main_layout_category_pb;

    @BindView(R.id.banner_pb)
    ProgressBar banner_pb;
    @BindView(R.id.main_image_slider)
    SliderLayout sliderLayout;

    @BindView(R.id.test_list_rv)
    RecyclerView practice_list_rv;
    PracticeAdapter practiceAdapter;

    Activity c;
    @BindView(R.id.test_list_pb)
    ProgressBar practice_list_pb;
    @BindView(R.id.home_title_test)
    RelativeLayout home_title_test;

    @BindView(R.id.book_list_pb)
    ProgressBar book_list_pb;
    @BindView(R.id.home_title_book)
    RelativeLayout home_title_book;
    APIInterface apiService;
    private ArrayList<Book> bookList;
    private GravitySnapHelper h;
    private ArrayList<Practice> practice_list;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        c = getActivity();
        apiService = APIClient.getClient().create(APIInterface.class);

        //slider
        loadRootApi();
//        loadHomePage("api/home");
        sliderLayout.setPagerTransformer(true, new RotateDownTransformer());

        categories = new ArrayList<>();

        home_title_test.setOnClickListener(this);
        home_title_book.setOnClickListener(this);

        //test list
        practice_list = new ArrayList<>();
        practice_list_rv.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, false));
        practiceAdapter = new PracticeAdapter(c, practice_list);
        practice_list_rv.setAdapter(practiceAdapter);
        SnapHelper h = new GravitySnapHelper(Gravity.START);
        h.attachToRecyclerView(practice_list_rv);

        //create book list
        bookList = new ArrayList<>();
        book_list_rv.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, false));
        bookAdapter = new BookAdapter(c, bookList);
        book_list_rv.setAdapter(bookAdapter);
        h = new GravitySnapHelper(Gravity.START);
        h.attachToRecyclerView(book_list_rv);

    }

    void loadRootApi(){
        Call<RootApiResponse> call  = apiService.loadApiList();
        call.enqueue(new Callback<RootApiResponse>() {
            @Override
            public void onResponse(Call<RootApiResponse> call, Response<RootApiResponse> response) {
                if(response.isSuccessful()) {
                    saveApi(response.body().getApiList());
                    loadHomePage(response.body().getApiList().get_api_home());
                }else{
                    showInfoDialog(c,"server error "+response.message());
                }
            }

            @Override
            public void onFailure(Call<RootApiResponse> call, Throwable t) {
                showInfoDialog(c,"get book failure");
            }
        });
    }

    private void saveApi(RootApiResponse.APIList apiList) {
        Utils.apiList = apiList;
    }

    private void loadHomePage(String api) {
        Call<HomeResponse> call = APIClient.getInterface().loadHomePageFromApi(api);
        call.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if(response.isSuccessful()) {
                    setBanner(response.body().getData().getBanners());

                    setCategories(response.body().getData().getCategories());

                    setBookList(response.body().getData().getBooks());

                    setPracticeList(response.body().getData().getPractices());
                }else{
                    showInfoDialog(c,response.message());
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                showInfoDialog(c,t.getMessage());
            }
        });
    }

    private void setPracticeList(ArrayList<Practice> practices) {
        practice_list_pb.setVisibility(View.GONE);
        practice_list.addAll(practices);
        practiceAdapter.notifyDataSetChanged();
    }

    private void setBookList(ArrayList<Book> books) {
        book_list_pb.setVisibility(View.GONE);
        bookList.addAll(books);
        bookAdapter.notifyDataSetChanged();
    }

    private void setCategories(ArrayList<Category> categories) {
        main_layout_category_pb.setVisibility(View.GONE);
        int size = categories.size();
        int i_cat= 0;
        int i_row= 0;
        if(size > 0){
            int numOfRows = (size+1)/2;
            while(i_row<numOfRows && i_cat<size){
                View v = LayoutInflater.from(c).inflate(R.layout.item_category,null);
                //////////////
                final Category cc1 = categories.get(i_cat);

                RelativeLayout category1 = (RelativeLayout) v.findViewById(R.id.category1);
                ImageView category_image_1 = (ImageView) v.findViewById(R.id.category1_image);
                TextView category_text_1 = (TextView) v.findViewById(R.id.category1_name);

                category1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(c,CategoryActivity.class);
                        i.putExtra(INTENT_KEY_CATEGORY_NAME,cc1.getCategoryName());
                        i.putExtra(INTENT_KEY_ACTION_LECTURE,cc1.getActions().getActionsLectures());
                        startActivity(i);
                    }
                });
                category_text_1.setText(cc1.getCategoryName());
                Picasso.with(c).load(cc1.getCategoryImageUrl()).into(category_image_1);
                i_cat++;

                //////////////
                final Category cc2 = categories.get(i_cat);
                RelativeLayout category2 = (RelativeLayout) v.findViewById(R.id.category2);
                ImageView category_image_2 = (ImageView) v.findViewById(R.id.category2_image);
                TextView category_text_2 = (TextView) v.findViewById(R.id.category2_name);
                category_text_2.setText(cc2.getCategoryName());
                Picasso.with(c).load(cc2.getCategoryImageUrl()).into(category_image_2);
                category2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(c,CategoryActivity.class);
                        i.putExtra(INTENT_KEY_CATEGORY_NAME,cc2.getCategoryName());
                        i.putExtra(INTENT_KEY_ACTION_LECTURE,cc2.getActions().getActionsLectures());
                        startActivity(i);
                    }
                });
                i_cat++;

                i_row++;
                main_layout_category.addView(v);
            }
        }
    }

    private void setBanner(ArrayList<Banner> ba) {
        banner_pb.setVisibility(View.GONE);
        for(Banner b : ba) {
            TextSliderView textSliderView = new TextSliderView(c);
            textSliderView
                    .description(b.getBanerTitle())
                    .image(b.getBannerImageUrl())
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", b.getBanerTitle());

            sliderLayout.addSlider(textSliderView);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_title_test:
                startActivity(new Intent(c, PracticeListActivity.class));
                break;
            case R.id.home_title_book:
                startActivity(new Intent(c, BookListActivity.class));
                break;
        }
    }

}
