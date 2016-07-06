package com.android.component.view.androidSwipeableViewComponent;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 7 on 4/27/2016.
 */
public class SwipeableView extends RelativeLayout {
    private Context context;
    private View left_view = null;
    private View top_view = null;
    private View right_view = null;
    private View bottom_view = null;
    private int view_x0,view_y0,view_width,view_height;
    private int left_view_x0,left_view_y0,left_view_width,left_view_height;
    private int top_view_x0,top_view_y0,top_view_width,top_view_height;
    private int right_view_x0,right_view_y0,right_view_width,right_view_height;
    private int bottom_view_x0,bottom_view_y0,bottom_view_width,bottom_view_height;


    public SwipeableView(Context context) {
        super(context);
        this.context=context;
        buildView();
    }

    public SwipeableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        buildView();
    }

    public SwipeableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        buildView();
    }

    public SwipeableView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context=context;
        buildView();
    }

    private void buildView() {
        int[] location=new int[2];
        this.getLocationOnScreen(location);
        view_x0=location[0];
        view_y0=location[1];
        view_width=this.getWidth();
        view_height=this.getHeight();
        int child_counts = this.getChildCount();
        if (child_counts > 0) {
            if (child_counts == 1) {
                left_view = this.getChildAt(0);
                left_view.getLocationOnScreen(location);
                left_view_x0=location[0];
                left_view_y0=location[1];
                left_view_width=left_view.getWidth();
                left_view_height=left_view.getHeight();
            }
            if (child_counts > 1) {
                top_view = this.getChildAt(1);
                top_view.getLocationOnScreen(location);
                top_view_x0=location[0];
                top_view_y0=location[1];
                top_view_width=top_view.getWidth();
                top_view_height=top_view.getHeight();
            }
            if (child_counts > 2) {
                right_view = this.getChildAt(2);
                right_view.getLocationOnScreen(location);
                right_view_x0=location[0];
                right_view_y0=location[1];
                right_view_width=right_view.getWidth();
                right_view_height=right_view.getHeight();
            }
            if (child_counts > 3) {
                bottom_view = this.getChildAt(3);
                bottom_view.getLocationOnScreen(location);
                bottom_view_x0=location[0];
                bottom_view_y0=location[1];
                bottom_view_width=bottom_view.getWidth();
                bottom_view_height=bottom_view.getHeight();
            }
        }
        this.setBackgroundColor(Color.YELLOW);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"salam", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
