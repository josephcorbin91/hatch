package com.google.samples.apps.hatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.samples.apps.hatch.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}