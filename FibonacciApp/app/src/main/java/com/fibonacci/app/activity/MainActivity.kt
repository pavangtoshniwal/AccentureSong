package com.fibonacci.app.activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fibonacci.app.databinding.ActivityMainBinding
import com.fibonacci.app.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.movementMethod = ScrollingMovementMethod()

        lifecycleScope.launchWhenStarted {
            mainViewModel.fibonacciSeries()
            mainViewModel.numberLiveData.observe(this@MainActivity){
                val resultText = binding.textView.text.toString()
                binding.textView.text = resultText.trim().plus("\n").plus(it.toString())
            }
        }
    }
}