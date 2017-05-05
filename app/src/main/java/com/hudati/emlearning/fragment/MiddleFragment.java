package com.hudati.emlearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.BookAdapter;
import com.hudati.emlearning.base.BaseFragment;
import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.util.Utils;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;

import static com.hudati.emlearning.util.Utils.calculateNumOfColumns;

/**
 * Created by huylv on 27-Mar-17.
 */

public class MiddleFragment extends BaseFragment {
    @BindView(R.id.book_list_pb)
    ProgressBar book_list_pb;
    @BindView(R.id.book_list_rv)
    RecyclerView book_list_rv;
    BookAdapter bookAdapter;
    private ArrayList<Book> bookList;
    @BindView(R.id.middle_no_book)
    TextView middle_no_book;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_middle;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookList = new ArrayList<>();
        for (Book b : Utils.bookDownloaded) {
            bookList.add(b);
        }

        if (bookList.size() == 0) middle_no_book.setVisibility(View.VISIBLE);

        bookAdapter = new BookAdapter(activity, bookList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, calculateNumOfColumns(activity, (int) getResources().getDimension(R.dimen.item_book_width)));
        book_list_rv.setLayoutManager(gridLayoutManager);
        book_list_rv.setAdapter(bookAdapter);
        book_list_pb.setVisibility(View.GONE);
    }

}
