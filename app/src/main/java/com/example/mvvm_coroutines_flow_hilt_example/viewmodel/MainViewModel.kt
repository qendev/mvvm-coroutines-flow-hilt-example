package com.example.mvvm_coroutines_flow_hilt_example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutines_flow_hilt_example.data.Movie
import com.example.mvvm_coroutines_flow_hilt_example.data.NetworkResult
import com.example.mvvm_coroutines_flow_hilt_example.repository.MainRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
ViewModel setup with Livedata
In the ViewModel collect the flow and set the response to livedata for UI changes.
* */

@HiltViewModel
class MainViewModel @Inject constructor(private  val mainRepository: MainRepository):ViewModel() {

    private var _movieResponse = MutableLiveData<NetworkResult<List<Movie>>>()

    val movieResponse:LiveData<NetworkResult<List<Movie>>> = _movieResponse

    init {
        fetchAllMovies()
    }

    private fun fetchAllMovies() {
        viewModelScope.launch {
            mainRepository.getPopularMovies().collect(){
                _movieResponse.postValue(it)
            }
        }
    }


}