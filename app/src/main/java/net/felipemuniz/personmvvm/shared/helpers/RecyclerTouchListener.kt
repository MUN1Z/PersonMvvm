package net.felipemuniz.personmvvm.shared.helpers

import android.content.Context
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * Created by Muniz on 05/07/2017.
 */

class RecyclerTouchListener(context: Context, recyclerView: RecyclerView,
                            private val mOnTouchActionListener: OnTouchActionListener?) : RecyclerView.OnItemTouchListener {
    private val mGestureDetector: GestureDetectorCompat

    interface OnTouchActionListener {
        fun onLeftSwipe(view: View, position: Int)
        fun onRightSwipe(view: View, position: Int)
        fun onClick(view: View, position: Int)
    }

    init {
        mGestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {

            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                Log.d(TAG, "On Single Tap Confirmed")
                // Find the item view that was swiped based on the coordinates
                val child = recyclerView.findChildViewUnder(e.x, e.y)
                val childPosition = recyclerView.getChildPosition(child)
                mOnTouchActionListener!!.onClick(child, childPosition)
                return false
            }

            override fun onFling(e1: MotionEvent, e2: MotionEvent,
                                 velocityX: Float, velocityY: Float): Boolean {
                Log.d(TAG, "onFling: " + e1.toString() + e2.toString())

                try {
                    if (Math.abs(e1.y - e2.y) > SWIPE_MAX_OFF_PATH) {
                        return false
                    }

                    // Find the item view that was swiped based on the coordinates
                    val child = recyclerView.findChildViewUnder(e1.x, e1.y)
                    val childPosition = recyclerView.getChildPosition(child)

                    // right to left swipe
                    if (e1.x - e2.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                        Log.d(TAG, "Left Swipe")
                        if (mOnTouchActionListener != null && child != null) {
                            mOnTouchActionListener.onLeftSwipe(child, childPosition)
                        }

                    } else if (e2.x - e1.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        Log.d(TAG, "Right Swipe")
                        if (mOnTouchActionListener != null && child != null) {
                            mOnTouchActionListener.onRightSwipe(child, childPosition)
                        }
                    }
                } catch (e: Exception) {
                    // nothing
                }

                return false
            }
        })
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        mGestureDetector.onTouchEvent(e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        // do nothing
    }

    companion object {

        private val TAG = "RecyclerTouchListener"

        private val SWIPE_MIN_DISTANCE = 120
        private val SWIPE_THRESHOLD_VELOCITY = 200
        private val SWIPE_MAX_OFF_PATH = 250
    }

}
