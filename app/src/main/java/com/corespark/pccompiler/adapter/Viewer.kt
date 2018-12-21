package com.corespark.pccompiler.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.corespark.pccompiler.R
import com.corespark.pccompiler.model.Compilation


/**
 * @author Zachy Bazov.
 * @since 21/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
enum class Layout(val layoutRes: Int) {
    FRAG_WORKSPACE(R.layout.fragment_workspace),
    FRAG_CART(R.layout.fragment_cart)
}

class Viewer(val context: Context) : PagerAdapter() {

    override fun getCount(): Int {
        return Layout.values().size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = LayoutInflater.from(context).inflate(Layout.values()[position].layoutRes, container, false)
        when (position) {
            0 -> {
                Compilation.add()
                layout.findViewById<RecyclerView>(R.id.rvCompilation).adapter = CompilationBar(context, Compilation.list)
            }
        }
        container.addView(layout)
        return layout
    }
}