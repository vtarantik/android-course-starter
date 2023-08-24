package com.tarantik.android_course_starter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tarantik.android_course_starter.ui.MoviesListUiModel
import cz.tarantik.android_course_starter.R
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: MoviesListViewModel by viewModels()

        val moviesAdapter = MoviesListAdapter()


        requireView().findViewById<RecyclerView>(R.id.rv_movies).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesAdapter
        }

        lifecycleScope.launch {
            viewModel.state.collect {
                when(it) {
                    is MoviesListUiModel.Success -> {
                        moviesAdapter.submitList(it.movies)
                    }

                    is MoviesListUiModel.Error -> {
                        Log.d("MLF", "Error in fragment: ${it.error.message}")
                    }
                    MoviesListUiModel.Loading -> {}
                }
            }
        }
    }
}