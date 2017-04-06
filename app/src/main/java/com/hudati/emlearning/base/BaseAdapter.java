package com.hudati.emlearning.base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by huylv on 21-Mar-17.
 */

public abstract class BaseAdapter<T, VH extends BaseAdapter.SViewHolder> extends RecyclerView.Adapter<VH> {
    protected Activity activity;
    public ArrayList<T> objectList;

    public BaseAdapter(Activity a, ArrayList<T> l){
        activity = a;
        objectList = l;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(getLayoutId(),parent,false);
        return onCreateViewHolder(v);
    }

    protected abstract VH onCreateViewHolder(View view);

    @Override
    public void onBindViewHolder(SViewHolder holder, int position) {
        onSBindViewHolder(holder,position,objectList.get(position));
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    protected abstract int getLayoutId();

    protected abstract void onSBindViewHolder(SViewHolder holder, int position, T t);

    public static class SViewHolder extends RecyclerView.ViewHolder {
        public SViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
