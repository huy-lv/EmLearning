package com.hudati.emlearning.custom;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.model.Category;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huylv on 22-Mar-17.
 */

public class CategoryView extends View {
    Category category;
    Context context;
    @BindView(R.id.category_image) ImageView category_image;
    @BindView(R.id.category_name)    TextView category_name;

    public CategoryView(Context context) {
        super(context);
        this.context = context;
    }

    public CategoryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CategoryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CategoryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Category c){
        category = c;
        View v = LayoutInflater.from(context).inflate(R.layout.item_category,null);
        ButterKnife.bind(this,v);

        category_name.setText(category.name);
        category_image.setImageResource(category.imageid);
    }
}
