package com.radicallabsinc.pakarhero.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class NoSwipeViewPager extends ViewPager {
    private boolean enabled;

    public NoSwipeViewPager(Context context){
        super(context);
    }

    public NoSwipeViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
        this.enabled = false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(this.enabled)
            return super.onInterceptTouchEvent(ev);
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(this.enabled)
            return super.onTouchEvent(ev);
        else
            return false;
    }

    public void setPagingEnabled(boolean enabled){
        this.enabled = enabled;
    }

    @Override
    public boolean executeKeyEvent(@NonNull KeyEvent event) {
        return enabled ? super.executeKeyEvent(event) : false;
    }
}
