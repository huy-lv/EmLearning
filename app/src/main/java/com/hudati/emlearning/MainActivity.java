package com.hudati.emlearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.simple_rv)
    RecyclerView simple_rv;
    ArrayList<Book> bookList;
    BookAdapter bookAdapter;



//    @BindView(R.id.simple_rv)    SimpleRecyclerView simple_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        //createbooklist
//        ArrayList<Book> books = Utils.getBookList(this);
//        for(Book b:books){
//            simple_rv.addCell(new BookCell(b));
//            Log.e("cxz","ad cell "+b.name);
//        }



        //create book list
        bookList = Utils.getBookList(this);
        simple_rv.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(this,bookList);
        simple_rv.setAdapter(bookAdapter);



    }
}
