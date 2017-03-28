package com.hudati.emlearning.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.BookAdapter;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by huylv on 27-Mar-17.
 */

public class BookListActivity extends BaseToolbarActivity {
    @BindView(R.id.book_list_rv)
    RecyclerView book_list_rv;
    BookAdapter bookAdapter;
    ArrayList<Book> bookList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookList = Utils.getBookList(this);
        bookAdapter = new BookAdapter(this, bookList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        book_list_rv.setLayoutManager(gridLayoutManager);
        book_list_rv.setAdapter(bookAdapter);

    }
}
