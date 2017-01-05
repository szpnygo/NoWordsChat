package info.smemo.nbaseaction.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;

import info.smemo.nbaseaction.R;

/**
 * CoordinatorLayout滑动控制器
 */
public class CollapsingViewBehavior extends CoordinatorLayout.Behavior<View> {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    //动画知否正在进行
    private boolean isAnimationIng = false;

    private int splitHeight = -1;
    private int totalScrollRange = -1;

    private float multiplier;

    public CollapsingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewBehaviorDependency);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            if (a.getIndex(i) == R.styleable.ViewBehaviorDependency_behaviorMultiplier) {
                multiplier = a.getFloat(attr, -1.0f);
            }
            if (a.getIndex(i) == R.styleable.ViewBehaviorDependency_behaviorHeight) {
                splitHeight = (int) a.getDimension(attr, -1);
            }
        }
        if (splitHeight == -1 && multiplier == -1) {
            multiplier = 0.7f;
        }
        a.recycle();
    }

    //在嵌套滑动开始前回调
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;//判断是否竖直滚动
    }

    //在嵌套滑动进行时，对象消费滚动距离前回调
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        //控件必须有高度，而且当时没有动画执行
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (totalScrollRange == -1 || splitHeight == -1) {
            totalScrollRange = ((AppBarLayout) dependency).getTotalScrollRange();
            splitHeight = (int) (totalScrollRange * multiplier);
        }
        if (splitHeight > 0 && dependency instanceof AppBarLayout) {
            if (!isAnimationIng) {
//                Log.e("Behavior", "dependency.getY:" + dependency.getY() + " totalScrollRange:" + totalScrollRange + " splitHeight:" + splitHeight);
                if (totalScrollRange + dependency.getY() <= splitHeight && child.getVisibility() == View.VISIBLE) {
                    hide(child);
                } else if (totalScrollRange + dependency.getY() >= splitHeight && child.getVisibility() == View.GONE) {
                    show(child);
                }
            }
        }
        if (totalScrollRange != -1) {
            if (totalScrollRange + dependency.getY() == 0) {
                scrollToTop(child, dependency);
            } else if (dependency.getY() == 0) {
                scrollToBottom(child, dependency);
            }
        }
        return super.onDependentViewChanged(parent, child, dependency);
    }

    //隐藏时的动画
    protected void hide(final View view) {
        isAnimationIng = true;
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setInterpolator(INTERPOLATOR);
        scaleAnimation.setDuration(200);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
                isAnimationIng = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
        view.startAnimation(scaleAnimation);
    }

    //显示时的动画
    protected void show(final View view) {
        isAnimationIng = true;
        view.setVisibility(View.VISIBLE);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setInterpolator(INTERPOLATOR);
        scaleAnimation.setDuration(200);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimationIng = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
        view.startAnimation(scaleAnimation);
    }

    //滑到到顶部
    protected void scrollToTop(View view, View dependency) {

    }

    //滑动到底部
    protected void scrollToBottom(View view, View dependency) {

    }
}
