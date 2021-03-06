package com.example.databindingdemo.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.databindingdemo.BR;
import com.example.databindingdemo.function.BiFunction;

import me.drakeet.multitype.ItemViewBinder;

/**
 * item数据 绑定类
 * Created by HuangBin on 2018/5/4.
 */
public class DataBindingItemViewBinder<T, DB extends ViewDataBinding>
        extends ItemViewBinder<T, DataBindingViewHolder<DB>> {

    private final Delegate<T, DB> delegate;

    public DataBindingItemViewBinder(Delegate<T, DB> delegate) {
        this.delegate = delegate;
    }

    public DataBindingItemViewBinder(BiFunction<LayoutInflater, ViewGroup, DB> factory,
                                     OnBindItem<T, DB> binder) {
        this(new SimpleDelegate<>(factory, binder));
    }

    public DataBindingItemViewBinder(@LayoutRes int resId, OnBindItem<T, DB> binder) {
        this((inflater, parent) -> DataBindingUtil.inflate(inflater, resId, parent, false), binder);

/*        this(new BiFunction<LayoutInflater, ViewGroup, DB>() {

            @Override
            public DB apply(LayoutInflater inflater, ViewGroup parent) {
                return DataBindingUtil.inflate(inflater, resId, parent, false);
            }

        }, binder);*/
    }

    @NonNull
    @Override
    protected DataBindingViewHolder<DB> onCreateViewHolder(@NonNull LayoutInflater inflater,
                                                           @NonNull ViewGroup parent) {
        return new DataBindingViewHolder<>(delegate.onCreateDataBinding(inflater, parent));
    }

    @Override
    protected void onBindViewHolder(@NonNull DataBindingViewHolder<DB> holder, @NonNull T item) {
        final DB binding = holder.dataBinding;
        //数据绑定对应的item layout
        binding.setVariable(BR.data, item);
        //回调
        delegate.onBind(binding, item, holder.getAdapterPosition());
        binding.executePendingBindings();
    }

    public interface Delegate<T, DB extends ViewDataBinding> {
        DB onCreateDataBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
        void onBind(@NonNull DB dataBinding, @NonNull T item, int position);
    }

    public interface OnBindItem<T, DB extends ViewDataBinding> {
        void bind(DB dataBinding, T data, int position);
    }

    private static class SimpleDelegate<T, DB extends ViewDataBinding> implements Delegate<T, DB> {

        private final BiFunction<LayoutInflater, ViewGroup, DB> factory;
        private final OnBindItem<T, DB> binder;

        SimpleDelegate(BiFunction<LayoutInflater, ViewGroup, DB> factory, OnBindItem<T, DB> binder) {
            this.factory = factory;
            this.binder = binder;
        }

        @Override
        public DB onCreateDataBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
            return factory.apply(inflater, parent);
        }

        @Override
        public void onBind(@NonNull DB dataBinding, @NonNull T item, int position) {
            binder.bind(dataBinding, item, position);
        }

    }

}
