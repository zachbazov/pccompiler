package com.corespark.pccompiler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.adapter.Recycler
import com.corespark.pccompiler.app.Application.Companion.attributes
import com.corespark.pccompiler.model.Bar
import com.corespark.pccompiler.model.Compilation
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.service.Intent
import com.corespark.pccompiler.utility.Array
import kotlinx.android.synthetic.main.activity_compile.*

/**
 * @author Zachy Bazov.
 * @since 18/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Compile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compile)

        Compilation.assignCompilation(Auth.parseUser, Compilation.title)

        customize()
    }

    private fun customize() {
        ivBackward.setImageDrawable(attributes.arrowIndicator(this))
        ivForward.setImageDrawable(attributes.arrowIndicator(this))
        ivWorkspace.setImageResource(R.drawable.ic_workspace_inactive)
        ivCart.setImageResource(R.drawable.ic_cart_inactive)

        tvCompilationTitle.text = Compilation.title

        val adapters = arrayOf(rvComponentBar, rvComponent)
        adapters.forEach { adapter -> setAdapter(adapter) }

        val clicks = arrayOf(clReturn, clFinish)
        clicks.forEach { click -> onClick(click) }
    }

    private fun setAdapter(view: View) = when (view) {
        rvComponentBar -> {
            Bar.Component.add()
            rvComponentBar.setHasFixedSize(true)
            rvComponentBar.adapter = Recycler(this, Bar.Component.list, 6, null)
        }
        else -> {
            rvComponent.setHasFixedSize(true)
            rvComponent.adapter = Recycler(this, Array.cpuList, 7, 0)
        }
    }

    private fun onClick(view: View) = when (view) {
        clReturn -> view.setOnClickListener {
            view.isSelected = true
            Intent.launch(this, R.layout.activity_workspace) {}
            Intent.finish(this)
        }
        else -> view.setOnClickListener {
            Intent.launch(this, R.layout.activity_workspace) {}
            Intent.finish(this)
            Compilation.isCompiling = true
        }
    }
}