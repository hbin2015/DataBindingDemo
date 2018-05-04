package com.example.databindingdemo.adapter;

import com.example.databindingdemo.function.Function;

public class Linker {
    private final Function<Object, Boolean> matcher;
    private final int layoutId;

    public static Linker of(Function<Object, Boolean> matcher, int layoutId) {
        return new Linker(matcher, layoutId);
    }

    public Linker(Function<Object, Boolean> matcher, int layoutId) {
        this.matcher = matcher;
        this.layoutId = layoutId;
    }

    public Function<Object, Boolean> getMatcher() {
        return matcher;
    }

    public int getLayoutId() {
        return layoutId;
    }

}