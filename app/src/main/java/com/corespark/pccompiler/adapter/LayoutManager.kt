package com.corespark.pccompiler.adapter

import android.content.Context
import android.graphics.PointF
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSmoothScroller
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics


/**
 * @author Zachy Bazov.
 * @since 31/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class LayoutManager(
    context: Context,
    orientation: Int,
    reverseLayout: Boolean,
    private val MILLISECONDS_PER_INCH: Float = 200f,
    private var isScrollEnabled: Boolean = true
) : LinearLayoutManager(context, orientation, reverseLayout) {

    init {
        setScrollEnabled(false)
    }

    fun setScrollEnabled(flag: Boolean) {
        isScrollEnabled = flag
    }

    override fun canScrollHorizontally(): Boolean = isScrollEnabled && super.canScrollHorizontally()

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        val smoothScroller = SmoothScroll(recyclerView.context)
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    private inner class SmoothScroll(context: Context) : LinearSmoothScroller(context) {

        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? =
            this@LayoutManager.computeScrollVectorForPosition(targetPosition)

        override fun getHorizontalSnapPreference(): Int = LinearSmoothScroller.SNAP_TO_START

        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float =
            MILLISECONDS_PER_INCH / displayMetrics.densityDpi
    }
}