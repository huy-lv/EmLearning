package com.hudati.emlearning.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hudati.emlearning.R;
import com.hudati.emlearning.activity.ReadBookActivity;
import com.hudati.emlearning.base.BaseAdapter;
import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.util.Utils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by huylv on 21-Mar-17.
 */

public class BookAdapter extends BaseAdapter<Book, BookAdapter.BookVH> {
    Gson gson;

    public BookAdapter(Activity a, ArrayList<Book> l) {
        super(a, l);
        gson = new Gson();
    }

    @Override
    protected BookVH onCreateViewHolder(View view) {
        return new BookVH(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_book;
    }

    @Override
    protected void onSBindViewHolder(SViewHolder holder, int position, final Book book) {
        BookVH bookVH = (BookVH) holder;
        bookVH.book_name.setText(book.getBookName());
        if (book.isDownloaded() && book.getBookImageUrl()!=null)
            Picasso.with(activity).load(new File(book.getBookImageUrl())).into(bookVH.book_image);
        else
            Picasso.with(activity).load(book.getBookImageUrl()).into(bookVH.book_image);
        bookVH.book_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, ReadBookActivity.class);
                i.putExtra(Utils.INTENT_KEY_BOOK_NAME, book.getBookName());
                i.putExtra(Utils.INTENT_KEY_BOOK_MP3, book.getBookAudio());
                i.putExtra(Utils.INTENT_KEY_BOOK_IMAGE, book.getBookImageUrl());
                i.putExtra(Utils.INTENT_KEY_BOOK_URL, book.isDownloaded() ? book.getFilePath() : book.getBookUrl());
                activity.startActivity(i);
            }
        });
        bookVH.item_book_download_iv.setVisibility(book.isDownloaded() ? View.VISIBLE : View.GONE);
    }

    class BookVH extends BaseAdapter.SViewHolder {
        @BindView(R.id.item_book_download_iv)
        ImageView item_book_download_iv;
        @BindView(R.id.book_name)
        TextView book_name;
        @BindView(R.id.book_image)
        ImageView book_image;
        @BindView(R.id.book_cv)
        CardView book_cv;

        BookVH(View itemView) {
            super(itemView);
        }
    }
}
