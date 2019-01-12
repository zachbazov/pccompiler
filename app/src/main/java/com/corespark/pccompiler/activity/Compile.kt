package com.corespark.pccompiler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.R.layout.activity_workspace
import com.corespark.pccompiler.adapter.Recycler
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.model.Bar
import com.corespark.pccompiler.service.Intent
import com.corespark.pccompiler.utility.KEY_COMPILATION_TITLE
import kotlinx.android.synthetic.main.activity_compile.*


class Compile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compile)

        val compilationTitle = intent.extras?.getString(KEY_COMPILATION_TITLE)
        tvCompilationTitle.text = compilationTitle.toString()

        customize()

        clReturn.setOnClickListener {
            Intent.launch(this, activity_workspace) {}
            Intent.finish(this)
        }
    }

    private fun customize() {
        val adapters = arrayOf(rvComponentBar, rvComponent)
        for (adapter in adapters) setAdapter(adapter)
    }

    private fun setAdapter(view: View) {
        when (view.id) {
            R.id.rvComponentBar -> {
                Bar.Component.add()
                rvComponentBar.adapter = Recycler(this, Bar.Component.list, 6, 0)
            }
            rvComponent.id -> {
                rvComponent.adapter = Recycler(this, Compiler.cpuList, 7, 0)
                rvComponent.setHasFixedSize(true)
            }
        }
    }
}


