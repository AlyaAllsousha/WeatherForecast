package com.example.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Constant
import com.example.myapplication.api.NetworResponse
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworResponse<WeatherModel>> = _weatherResult
    fun getData(city: String) {
        _weatherResult.value = NetworResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constant.apiKey, city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworResponse.Success(it)
                    }
                } else {
                    _weatherResult.value = NetworResponse.Error("Fail to load info")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworResponse.Error("Fail to load info")

            }

        }

    }
}