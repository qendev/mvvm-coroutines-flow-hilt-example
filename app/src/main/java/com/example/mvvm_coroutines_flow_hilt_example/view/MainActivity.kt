package com.example.mvvm_coroutines_flow_hilt_example.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.mvvm_coroutines_flow_hilt_example.R
import com.example.mvvm_coroutines_flow_hilt_example.data.Movie
import com.example.mvvm_coroutines_flow_hilt_example.data.NetworkResult
import com.example.mvvm_coroutines_flow_hilt_example.databinding.ActivityMainBinding
import com.example.mvvm_coroutines_flow_hilt_example.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel:MainViewModel by viewModels()

    @Inject
    lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.adapter = movieAdapter

        movieAdapter.setItemClick(object : MovieAdapter.ClickInterface<Movie> {
            override fun onClick(data: Movie) {
                Toast.makeText(this@MainActivity, data.title, Toast.LENGTH_SHORT).show()
            }
        })

        mainViewModel.movieResponse.observe(this){
            when(it){
                is NetworkResult.Loading->{
                    binding.progressbar.isVisible =it.isLoading

                }

                is NetworkResult.Failure->{
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    binding.progressbar.isVisible = false
                }

                is NetworkResult.Success->{
                    movieAdapter.updateMovies(it.data)
                    binding.progressbar.isVisible = false
                }
            }
        }
    }
}