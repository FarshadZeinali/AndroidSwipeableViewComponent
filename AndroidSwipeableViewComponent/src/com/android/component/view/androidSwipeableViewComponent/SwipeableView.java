package com.android.component.view.androidSwipeableViewComponent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

/**
 * @author Farshad Zeinali
 * @version 1.0
 * @since 1395/04/17
 * <p>farshad.zeinaly@gmail.com</p>
 * <p>https://github.com/FarshadZeinali/AndroidSwipeableViewComponent</p>
 */
public class SwipeableView extends RelativeLayout implements View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    private boolean isGlobalLayoutListenerActive = false;
    private View left_view = null;
    private View right_view = null;
    private View content_view = null;
    private int view_x0, view_y0, view_width, view_height;
    private int left_view_x0, left_view_y0, left_view_width, left_view_height;
    private int right_view_x0, right_view_y0, right_view_width, right_view_height;
    private int content_view_x0, content_view_y0, content_view_width, content_view_height;
    private boolean is_move_left_view = false;
    private boolean is_move_right_view = false;
    private boolean is_show_left_view = false;
    private boolean is_show_right_view = false;

    //------------------------------------------------------------------------------------------------------------------
    public SwipeableView(Context context) {
        super(context);
    }

    //------------------------------------------------------------------------------------------------------------------
    public SwipeableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //------------------------------------------------------------------------------------------------------------------
    public SwipeableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //------------------------------------------------------------------------------------------------------------------
    public SwipeableView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isGlobalLayoutListenerActive) {
            buildView();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    private void buildView() {
        int child_counts = this.getChildCount();
        if (child_counts > 0) {
            if (child_counts > 0) {
                left_view = this.getChildAt(0);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) left_view.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, this.getId());
                left_view.setLayoutParams(params);
            }
            if (child_counts > 1) {
                right_view = this.getChildAt(1);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) right_view.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, this.getId());
                right_view.setLayoutParams(params);
            }
            if (child_counts > 2) {
                content_view = this.getChildAt(2);
            }
        }
        this.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onGlobalLayout() {
        if (!isGlobalLayoutListenerActive) {
            isGlobalLayoutListenerActive = true;
            int[] location = new int[2];
            this.getLocationInWindow(location);
            view_x0 = location[0];
            view_y0 = location[1];
            view_width = this.getWidth();
            view_height = this.getHeight();

            horizontally_min_different = view_width / 20;

            if (left_view != null) {
                left_view.getLocationInWindow(location);
                left_view_x0 = location[0];
                left_view_y0 = location[1];
                left_view_width = left_view.getWidth();
                left_view_height = left_view.getHeight();
                left_view.setTranslationX(-left_view_width);
                left_view.invalidate();
            }
            if (right_view != null) {
                right_view.getLocationInWindow(location);
                right_view_x0 = location[0];
                right_view_y0 = location[1];
                right_view_width = right_view.getWidth();
                right_view_height = right_view.getHeight();
                right_view.setTranslationX(right_view_width);
                right_view.invalidate();
            }
            if (content_view != null) {
                content_view.getLocationInWindow(location);
                content_view_x0 = location[0];
                content_view_y0 = location[1];
                content_view_width = content_view.getWidth();
                content_view_height = content_view.getHeight();
                content_view.invalidate();
            }

            this.setOnTouchListener(this);
            this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    private float view_raw_x0;
    private float view_touch_x0;
    private float view_touch_x1;

    private float horizontally_different = 0.0f;

    private float horizontally_min_different;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == this.getId()) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                view_touch_x0 = event.getX();
                view_raw_x0 = event.getRawX();
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                view_touch_x1 = event.getX();
                horizontally_different = view_touch_x1 - view_touch_x0;

                if (Math.abs(horizontally_different) >= Math.abs(horizontally_min_different)) {
                    if (view_raw_x0 < view_width / 2) {
                        if (horizontally_different > 0) {
                            this.post(new Runnable() {
                                @Override
                                public void run() {
                                    moveLeftChildToRight();
                                }
                            });

                        } else {
                            this.post(new Runnable() {
                                @Override
                                public void run() {
                                    moveLeftChildToLeft();
                                }
                            });

                        }
                    } else {
                        if (view_touch_x0 > view_touch_x1) {
                            this.post(new Runnable() {
                                @Override
                                public void run() {
                                    moveRightChildToLeft();
                                }
                            });

                        } else {
                            this.post(new Runnable() {
                                @Override
                                public void run() {
                                    moveRightChildToRight();
                                }
                            });
                        }
                    }
                }
            }
            if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                if (is_move_left_view) {
                    if (left_view.getTranslationX() >= view_x0 + (left_view_width / 3)) {
                        this.post(new Runnable() {
                            @Override
                            public void run() {
                                showLeftChildInRight();
                            }
                        });

                    } else {
                        this.post(new Runnable() {
                            @Override
                            public void run() {
                                showLeftChildInLeft();
                            }
                        });
                    }
                }
                if (is_move_right_view) {
                    if (right_view.getTranslationX() <= view_width - (right_view_width / 3)) {
                        this.post(new Runnable() {
                            @Override
                            public void run() {
                                showRightChildInLeft();
                            }
                        });
                    } else {
                        this.post(new Runnable() {
                            @Override
                            public void run() {
                                showRightChildInRight();
                            }
                        });
                    }
                }
            }
        }
        return true;
    }

    //------------------------------------------------------------------------------------------------------------------
    public void moveLeftChildToRight() {
        if (left_view != null) {
            if (left_view.getTranslationX() < 0) {
                left_view.setTranslationX(left_view.getTranslationX() + 50);
                left_view.setVisibility(VISIBLE);
                left_view.invalidate();
                is_move_left_view = true;
                moveViewToRightLeft(content_view.getTranslationX() + 50);
            } else if (left_view.getTranslationX() >= 0) {
                left_view.setTranslationX(0);
                left_view.setVisibility(VISIBLE);
                left_view.invalidate();
                is_move_left_view = false;
                is_show_left_view = true;
                showLeftChildInRight();
                moveViewToRightLeft(left_view.getTranslationX() + left_view_width);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public void moveLeftChildToLeft() {
        if (left_view != null) {
            if (left_view.getTranslationX() > -left_view_width) {
                left_view.setTranslationX(left_view.getTranslationX() - 50);
                moveViewToRightLeft(content_view.getTranslationX() - 50);
                left_view.setVisibility(VISIBLE);
                left_view.invalidate();
                is_move_left_view = true;
            } else if (left_view.getTranslationX() <= -left_view_width) {
                left_view.setTranslationX(-left_view_width);
                left_view.setVisibility(INVISIBLE);
                left_view.invalidate();
                is_move_left_view = false;
                is_show_left_view = false;
                moveViewToRightLeft(0);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public void showLeftChildInRight() {
        if (left_view != null) {
            left_view.setTranslationX(0);
            left_view.setVisibility(VISIBLE);
            left_view.invalidate();
            is_move_left_view = false;
            is_show_left_view = true;
            showRightChildInRight();
            moveViewToRightLeft(left_view.getTranslationX() + left_view_width);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public void showLeftChildInLeft() {
        if (left_view != null) {
            left_view.setTranslationX(-left_view_width);
            left_view.setVisibility(INVISIBLE);
            left_view.invalidate();
            is_move_left_view = false;
            is_show_left_view = false;
            moveViewToRightLeft(0);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public void moveRightChildToLeft() {
        if (right_view != null) {
            if (right_view.getTranslationX() > 0) {
                right_view.setTranslationX(right_view.getTranslationX() - 50);
                right_view.setVisibility(VISIBLE);
                right_view.invalidate();
                is_move_right_view = true;
                moveViewToRightLeft(content_view.getTranslationX() - 50);
            } else if (right_view.getTranslationX() <= 0) {
                right_view.setTranslationX(0);
                right_view.setVisibility(VISIBLE);
                right_view.invalidate();
                is_move_right_view = false;
                is_show_right_view = true;
                showLeftChildInLeft();
                moveViewToRightLeft(-right_view_width);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public void moveRightChildToRight() {
        if (right_view != null) {
            if (right_view.getTranslationX() < right_view_width) {
                right_view.setTranslationX(right_view.getTranslationX() + 50);
                right_view.setVisibility(VISIBLE);
                is_move_right_view = true;
                right_view.invalidate();
                moveViewToRightLeft(content_view.getTranslationX() + 50);
            } else if (right_view.getTranslationX() >= right_view_width) {
                right_view.setTranslationX(right_view_width);
                right_view.setVisibility(INVISIBLE);
                right_view.invalidate();
                is_move_right_view = false;
                is_show_right_view = false;
                moveViewToRightLeft(0);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public void showRightChildInLeft() {
        if (right_view != null) {
            right_view.setTranslationX(0);
            right_view.setVisibility(VISIBLE);
            right_view.invalidate();
            is_move_right_view = false;
            is_show_right_view = true;
            showLeftChildInLeft();
            moveViewToRightLeft(content_view.getTranslationX());
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public void showRightChildInRight() {
        if (right_view != null) {
            right_view.setTranslationX(right_view_width);
            right_view.setVisibility(INVISIBLE);
            right_view.invalidate();
            is_move_right_view = false;
            is_show_right_view = false;
            moveViewToRightLeft(0);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public void moveViewToRightLeft(float transactionX) {
        if (content_view != null) {
            content_view.setTranslationX(transactionX);
            content_view.invalidate();
            invalidate();
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
