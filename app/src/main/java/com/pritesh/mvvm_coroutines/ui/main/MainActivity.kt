package com.pritesh.mvvm_coroutines.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pritesh.mvvm_coroutines.R
import com.pritesh.mvvm_coroutines.databinding.ActivityMainBinding
import com.pritesh.mvvm_coroutines.ui.main.adapter.AleAdapter
import com.pritesh.mvvm_coroutines.ui.main.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setupObserver() {
        viewModel!!.responseObserver.observe(this) {
            binding!!.rvList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding!!.rvList.adapter = AleAdapter(this, it)
        }

        viewModel!!.statusObserver.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}