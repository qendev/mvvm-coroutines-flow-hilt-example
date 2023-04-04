package com.example.mvvm_coroutines_flow_hilt_example.repository

import com.example.mvvm_coroutines_flow_hilt_example.data.ApiService
import com.example.mvvm_coroutines_flow_hilt_example.data.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/*
On the repository, I am calling the API service and returning the response as flow to ViewModel.
Also, I am using NetworkResult sealed class to emit different statuses of the network call.
* */
class MainRepository @Inject constructor(private  val apiService: ApiService) {

    suspend fun getPopularMovies() = flow{
        emit(NetworkResult.Loading(true))
        val response = apiService.getMostPopularMovies()
        emit(NetworkResult.Success(response.items))
    }.catch{e ->
        emit(NetworkResult.Failure(e.message?:"Unkown Error"))

    }


}