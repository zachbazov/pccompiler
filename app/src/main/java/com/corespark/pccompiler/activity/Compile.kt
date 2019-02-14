package com.corespark.pccompiler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.adapter.Dialog
import com.corespark.pccompiler.adapter.Recycler
import com.corespark.pccompiler.app.Application
import com.corespark.pccompiler.model.Bar
import com.corespark.pccompiler.model.Compilation
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.service.Intent
import com.corespark.pccompiler.utility.Array
import kotlinx.android.synthetic.main.activity_compile.*


class Compile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compile)

        Compilation.assignCompilation(Auth.parseUser, Compilation.title)

        customize()
    }

    private fun customize() {
        ivBackward.setImageDrawable(Application.attributes.arrowIndicator(this))
        ivForward.setImageDrawable(Application.attributes.arrowIndicator(this))
        ivWorkspace.setImageResource(R.drawable.ic_workspace_inactive)
        ivCart.setImageResource(R.drawable.ic_cart_inactive)

        tvCompilationTitle.text = Compilation.title

        val adapters = arrayOf(rvComponentBar, rvComponent)
        for (adapter in adapters) setAdapter(adapter)

        val clicks = arrayOf(clReturn, clFinish)
        for (click in clicks) onClick(click)
    }

    private fun setAdapter(view: View) = when (view) {
        rvComponentBar -> {
            Bar.Component.add()
            rvComponentBar.adapter = Recycler(this, Bar.Component.list, 6, null)
        }
        else -> {
            rvComponent.adapter = Recycler(this, Array.cpuList, 7, 0)
            rvComponent.setHasFixedSize(true)
        }
    }

    private fun onClick(view: View) = when (view) {
        clReturn -> {
            view.setOnClickListener {
                view.isSelected = true
                val dialog = Dialog(this)
                dialog.Compile().Alert().build()
            }
        }
        else -> {
            view.setOnClickListener {
                Intent.launch(this, R.layout.activity_workspace) {
                    Compilation.isOnGoing = true
                }
                Intent.finish(this)
            }
        }
    }
}


