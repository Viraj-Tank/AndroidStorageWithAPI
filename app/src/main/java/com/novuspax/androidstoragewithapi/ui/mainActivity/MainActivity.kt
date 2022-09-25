package com.novuspax.androidstoragewithapi.ui.mainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.novuspax.androidstoragewithapi.databinding.ActivityMainBinding
import com.novuspax.androidstoragewithapi.ui.detailActivity.DetailActivity
import com.novuspax.androidstoragewithapi.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var ramAdapter: RAMAdapter? = null
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initSetup()
        ramAdapter = RAMAdapter { imageUrl, imageView ->
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@MainActivity, imageView, "fade"
            )
            startActivity(
                Intent(this@MainActivity, DetailActivity::class.java).putExtra(
                    "imageUrl",
                    imageUrl
                ), options.toBundle()
            )
        }
        binding.rvRAM.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = ramAdapter
        }
        lifecycleScope.launchWhenCreated {
            viewModel.RAMList.collect {
                ramAdapter?.submitData(it)
            }
        }

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