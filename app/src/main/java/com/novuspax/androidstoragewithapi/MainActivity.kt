package com.novuspax.androidstoragewithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import com.novuspax.androidstoragewithapi.databinding.ActivityMainBinding
import com.novuspax.androidstoragewithapi.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            viewModel.RAMList.observe(this@MainActivity) {
                it.map {
                    Log.e("TAG", "onCreate: ${it.id}", )
                }
            }
        }

    }
}