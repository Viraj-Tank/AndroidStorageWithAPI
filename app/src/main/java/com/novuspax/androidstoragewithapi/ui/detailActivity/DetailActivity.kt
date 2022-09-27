package com.novuspax.androidstoragewithapi.ui.detailActivity

import android.app.DownloadManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.transition.Fade
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.novuspax.androidstoragewithapi.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "DetailActivity"
    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private var imageUrl = ""
    var myDownloadId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initSetup()
        imageUrl = intent?.getStringExtra("imageUrl").toString()
        Glide.with(this).load(imageUrl).centerCrop().into(binding.imgRAM)
        binding.btnToCache.setOnClickListener(this)
        binding.btnToFiles.setOnClickListener(this)
        binding.btnDownloadManager.setOnClickListener(this)
        registerReceiver(broadcastReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

    }

    private fun initSetup() {
        val fade: Fade = Fade()
        val decor: View = window.decorView

        fade.excludeTarget(android.R.id.statusBarBackground, true)
        fade.excludeTarget(android.R.id.navigationBarBackground, true)

        window?.enterTransition = fade
        window?.exitTransition = fade
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnToCache -> {
                saveToPrivateCacheFolder()
            }
            binding.btnToFiles -> {
                saveToPrivateFilesFolder()
            }
            binding.btnDownloadManager -> {
                saveFileUsingDownloadManager()
            }
        }
    }

    private fun saveFileUsingDownloadManager() {
        val imageToBeDownload = DownloadManager.Request(Uri.parse(imageUrl))
            .setTitle(imageUrl.substring(0..10))
            .setDescription("Downloading Image...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)

        val downloadManager = getSystemService(Service.DOWNLOAD_SERVICE) as DownloadManager
        myDownloadId = downloadManager.enqueue(imageToBeDownload)
    }

    private fun saveToPrivateCacheFolder() {
//        val file = File(filesDir, "my folder")
//        if (file.exists().not()) {
//            file.mkdir()
//        }
//        Log.e(TAG, "saveToPrivateCacheFolder: $filesDir")

    }

    private fun saveToPrivateFilesFolder() {

    }

    private var broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (id == myDownloadId) {
                Toast.makeText(this@DetailActivity, "Download Complete", Toast.LENGTH_SHORT).show()
            }
        }
    }

}