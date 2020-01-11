package com.odia.alphabet.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by deeptiman on 12/12/2017.
 */

public class AnimateView {

    public static void animateVisibleView(final View view) {

        final int cx = view.getMeasuredWidth() / 2;
        final int cy = view.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        final int finalRadius = Math.max(view.getWidth(), view.getHeight()) / 2;

        // create the animator for this view (the start radius is zero)

        view.post(new Runnable() {
            @Override
            public void run() {
                //create your anim here
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                view.setVisibility(View.VISIBLE);
                anim.start();
            }
        });
    }


    public static void animateInVisibleView(final View view) {

        final int cx = view.getMeasuredWidth() / 2;
        final int cy = view.getMeasuredHeight() / 2;

        // get the initial radius for the clipping circle
        final int initialRadius = view.getWidth() / 2;

        // create the animation (the final radius is zero)
        view.post(new Runnable() {
            @Override
            public void run() {
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);

                // make the view invisible when the animation is done
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.INVISIBLE);
                    }
                });

                // start the animation
                anim.start();
            }
        });
    }
}
