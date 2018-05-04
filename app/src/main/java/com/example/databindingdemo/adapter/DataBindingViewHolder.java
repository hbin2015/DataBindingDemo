package com.example.databindingdemo.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * 视图句柄 绑定类
 * Created by HuangBin on 2018/5/4.
 */
public class DataBindingViewHolder<T extends ViewDataBinding>
        extends RecyclerView.ViewHolder {

    public final T dataBinding;

    public DataBindingViewHolder(T binding) {
        super(binding.getRoot());
        dataBinding = binding;
    }

}
