package com.hudati.emlearning;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hudati.emlearning.base.BaseAdapter;
import com.hudati.emlearning.model.Book;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by huylv on 21-Mar-17.
 */

public class BookAdapter extends BaseAdapter<Book> {
    public BookAdapter(Activity a, ArrayList<Book> l) {
        super(a, l);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_book;
    }

    @Override
    protected void onSBindViewHolder(SViewHolder holder, int position, Book book) {
        BookVH bookVH = (BookVH) holder;
        bookVH.book_name.setText(book.name);
        bookVH.book_thumb.setImageResource(book.imageid);
    }


    class BookVH extends SViewHolder {
        @BindView(R.id.book_name)        TextView book_name;
        @BindView(R.id.book_thumb)        ImageView book_thumb;

        public BookVH(View itemView) {
            super(itemView);
        }
    }
}
