package com.hudati.emlearning.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.RotateDownTransformer;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.hudati.emlearning.BookListActivity;
import com.hudati.emlearning.PracticeActivity;
import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.BookAdapter;
import com.hudati.emlearning.base.BaseFragment;
import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.model.Category;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by huylv on 22-Mar-17.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.book_list_rv)
    RecyclerView book_list_rv;
    ArrayList<Book> bookList;
    BookAdapter bookAdapter;

    @BindView(R.id.main_layout_category)
    LinearLayout main_layout_category;
    ArrayList<Category> categories;

    @BindView(R.id.main_image_slider)
    SliderLayout sliderLayout;
    @BindView(R.id.test_list_rv)
    RecyclerView test_list_rv;
    BookAdapter testAdapter;
    @BindView(R.id.home_category1)
    RelativeLayout home_category1;
    @BindView(R.id.home_category2)
    RelativeLayout home_category2;
    @BindView(R.id.home_category3)
    RelativeLayout home_category3;
    @BindView(R.id.home_category4)
    RelativeLayout home_category4;
    Activity c;
    @BindView(R.id.home_title_test)
    RelativeLayout home_title_test;
    @BindView(R.id.home_title_book)
    RelativeLayout home_title_book;
    private ArrayList<Book> testList;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        c = getActivity();

        //slider
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(c);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPagerTransformer(true, new RotateDownTransformer());


        //categoryList
        categories = Utils.getCategoryList();
        home_category1.setOnClickListener(this);
        home_category2.setOnClickListener(this);
        home_category3.setOnClickListener(this);
        home_category4.setOnClickListener(this);
        home_title_test.setOnClickListener(this);
        home_title_book.setOnClickListener(this);

        //test list
        testList = Utils.getBookList(c);
        test_list_rv.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, false));
        testAdapter = new BookAdapter(c, testList);
        test_list_rv.setAdapter(testAdapter);
        SnapHelper h = new GravitySnapHelper(Gravity.START);
        h.attachToRecyclerView(test_list_rv);


        //create book list
        bookList = Utils.getBookList(c);
        book_list_rv.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, false));
        bookAdapter = new BookAdapter(c, bookList);
        book_list_rv.setAdapter(bookAdapter);
        h = new GravitySnapHelper(Gravity.START);
        h.attachToRecyclerView(book_list_rv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_category1:
                startActivity(new Intent(c, PracticeActivity.class));
                break;
            case R.id.home_category2:

                break;
            case R.id.home_category3:

                break;
            case R.id.home_category4:

                break;
            case R.id.home_title_test:
                startActivity(new Intent(c, BookListActivity.class));
                break;
            case R.id.home_title_book:
                startActivity(new Intent(c, BookListActivity.class));
                break;
        }
    }

}
