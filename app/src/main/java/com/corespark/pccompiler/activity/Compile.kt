package com.corespark.pccompiler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.adapter.Recycler
import com.corespark.pccompiler.model.Bar
import kotlinx.android.synthetic.main.activity_compile.*


class Compile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compile)

        customize()
    }

    private fun customize() {
        val adapters = arrayOf(rvComponentBar)
        for (adapter in adapters) setAdapter(adapter)
    }

    private fun setAdapter(view: View) {
        when (view.id) {
            R.id.rvComponentBar -> {
                Bar.Component.add()
                rvComponentBar.adapter = Recycler(this, Bar.Component.list, 6)
            }
        }
    }
}
