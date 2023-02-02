package com.fibonacci.app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val _numberLiveData = MutableLiveData<Long>()
    val numberLiveData: LiveData<Long> = _numberLiveData

    fun fibonacciSeries() = viewModelScope.launch(Dispatchers.IO) {
        for (i in 1..100) {
            val result = fibonacci(i)
            if (result <= Long.MAX_VALUE) {
                Thread.sleep(100)
                withContext(Dispatchers.Main) {
                    Log.d("MainViewModel", "number: $i :: fibonacciSeries: $result")
                    _numberLiveData.value = result
                }
            }else{
                break
            }
        }
    }

    private fun fibonacci(n: Int): Long {
        return if (n <= 1) n.toLong()
        else fibonacci(n - 1) + fibonacci(n - 2)
    }
}