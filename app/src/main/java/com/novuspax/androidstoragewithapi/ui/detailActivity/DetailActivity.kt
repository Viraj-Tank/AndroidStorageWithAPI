package com.novuspax.androidstoragewithapi.ui.detailActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.view.View
import com.novuspax.androidstoragewithapi.R
import com.novuspax.androidstoragewithapi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fade: Fade = Fade()
        val decor: View = window.decorView

        fade.excludeTarget(android.R.id.statusBarBackground,true)
        fade.excludeTarget(android.R.id.navigationBarBackground,true)

        window?.enterTransition = fade
        window?.exitTransition = fade
    }
}