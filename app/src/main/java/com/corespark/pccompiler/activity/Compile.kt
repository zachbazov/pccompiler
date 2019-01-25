package com.corespark.pccompiler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.R.layout.activity_workspace
import com.corespark.pccompiler.adapter.Recycler
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.model.Bar
import com.corespark.pccompiler.model.Compilation
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.service.Intent
import kotlinx.android.synthetic.main.activity_compile.*


class Compile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compile)

        Compilation.assignCompilation(Auth.parseUser!!, Compilation.title)

        customize()
    }

    private fun customize() {
        val attr = theme.obtainStyledAttributes(R.style.AppTheme, intArrayOf(R.attr.homeAsUpIndicator))
        val attrResId = attr.getResourceId(0, 0)
        val drawable = resources.getDrawable(attrResId, theme)
        attr.recycle()
        ivWorkspace.setImageResource(R.drawable.ic_workspace_inactive)
        ivReturn.setImageDrawable(drawable)

        tvCompilationTitle.text = Compilation.title

        val adapters = arrayOf(rvComponentBar, rvComponent)
        for (adapter in adapters) setAdapter(adapter)

        val clicks = arrayOf(clReturn)
        for (click in clicks) onClick(click)
    }

    private fun setAdapter(view: View) = when (view.id) {
        rvComponentBar.id -> {
            Bar.Component.add()
            rvComponentBar.adapter = Recycler(this, Bar.Component.list, 6, null)
        }
        else -> {
            rvComponent.adapter = Recycler(this, Compiler.cpuList, 7, 0)
            rvComponent.setHasFixedSize(true)
        }
    }

    private fun onClick(view: View) = when (view.id) {
        clReturn.id -> view.setOnClickListener {
            Intent.launch(this, activity_workspace) {}
            Intent.finish(this)
        }
        else -> {}
    }
}


