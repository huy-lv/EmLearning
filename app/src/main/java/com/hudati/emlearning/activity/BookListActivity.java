package com.hudati.emlearning.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.BookAdapter;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.APIInterface;
import com.hudati.emlearning.api.BookListResponse;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hudati.emlearning.util.Utils.calculateNumOfColumns;
import static com.hudati.emlearning.util.Utils.showInfoDialog;

/**
 * Created by huylv on 27-Mar-17.
 */

public class BookListActivity extends BaseToolbarActivity {
    @BindView(R.id.book_list_pb)
    ProgressBar book_list_pb;
    @BindView(R.id.book_list_rv)
    RecyclerView book_list_rv;
    BookAdapter bookAdapter;
    private ArrayList<Book> bookList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(this, bookList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, calculateNumOfColumns(this, (int) getResources().getDimension(R.dimen.item_book_width)));
        book_list_rv.setLayoutManager(gridLayoutManager);
        book_list_rv.setAdapter(bookAdapter);
        loadBookList();
    }

    private void loadBookList() {
        Call<BookListResponse> call  = APIClient.getInterface().loadBookList(Utils.apiList.get_api_books());
        call.enqueue(new Callback<BookListResponse>() {
            @Override
            public void onResponse(Call<BookListResponse> call, Response<BookListResponse> response) {
                bookList.addAll(response.body().getData());
                bookAdapter.notifyDataSetChanged();
                book_list_pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BookListResponse> call, Throwable t) {
                showInfoDialog(BookListActivity.this,"get book failure");
            }
        });
    }
}
