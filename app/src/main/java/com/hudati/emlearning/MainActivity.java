package com.hudati.emlearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.RotateDownTransformer;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.hudati.emlearning.adapter.BookAdapter;
import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.model.Category;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.book_list_rv)    RecyclerView book_list_rv;
    ArrayList<Book> bookList;
    BookAdapter bookAdapter;

    @BindView(R.id.main_layout_category)    LinearLayout main_layout_category;
    ArrayList<Category> categories;

    @BindView(R.id.main_image_slider)    SliderLayout sliderLayout;

    private ArrayList<Book> testList;
    @BindView(R.id.test_list_rv) RecyclerView test_list_rv;
    BookAdapter testAdapter;

//    @BindView(R.id.book_list_rv)    SimpleRecyclerView book_list_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //slider
        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPagerTransformer(true,new RotateDownTransformer());



        //categoryList
        categories = Utils.getCategoryList();


        //test list
        testList = Utils.getBookList(this);
        test_list_rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        testAdapter = new BookAdapter(this,testList);
        test_list_rv.setAdapter(testAdapter);
        SnapHelper h = new GravitySnapHelper(Gravity.START);
        h.attachToRecyclerView(test_list_rv);


        //create book list
        bookList = Utils.getBookList(this);
        book_list_rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        bookAdapter = new BookAdapter(this,bookList);
        book_list_rv.setAdapter(bookAdapter);
        h = new GravitySnapHelper(Gravity.START);
        h.attachToRecyclerView(book_list_rv);


    }
}
