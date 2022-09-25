package com.novuspax.androidstoragewithapi.ui.detailActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.view.View
import com.bumptech.glide.Glide
import com.novuspax.androidstoragewithapi.R
import com.novuspax.androidstoragewithapi.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    var imageUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initSetup()
        imageUrl = intent?.getStringExtra("imageUrl").toString()
        Glide.with(this).load(imageUrl).into(binding.imgRAM)

    }

    private fun initSetup() {
        val fade: Fade = Fade()
        val decor: View = window.decorView

        fade.excludeTarget(android.R.id.statusBarBackground, true)
        fade.excludeTarget(android.R.id.navigationBarBackground, true)

        window?.enterTransition = fade
        window?.exitTransition = fade
    }
}