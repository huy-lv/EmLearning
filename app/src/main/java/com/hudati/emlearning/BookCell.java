package com.hudati.emlearning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hudati.emlearning.model.Book;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huylv on 21-Mar-17.
 */

public class BookCell extends SimpleCell<Book,BookCell.BookViewHolder> {

    public BookCell(Book item) {
        super(item);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_book;
    }

    @NonNull
    @Override
    protected BookViewHolder onCreateViewHolder(ViewGroup viewGroup, View view) {
        return new BookViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(BookViewHolder bookViewHolder, int i, Context context, Object o) {
        bookViewHolder.book_name.setText(getItem().name);
        bookViewHolder.book_thumb.setImageResource(getItem().imageid);
        Log.e("cxz","bind "+i+ " "+getItem().name);
    }

    @Override
    protected long getItemId() {
        return 0;
    }

    class BookViewHolder extends SimpleViewHolder{
        @BindView(R.id.book_name) TextView book_name;
        @BindView(R.id.book_thumb) ImageView book_thumb;

        BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
