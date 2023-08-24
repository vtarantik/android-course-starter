package com.tarantik.android_course_starter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.tarantik.android_course_starter.R

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: MoviesListViewModel by viewModels()

        val moviesAdapter = MoviesListAdapter()


        requireView().findViewById<RecyclerView>(R.id.rv_movies).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesAdapter
        }

        viewModel.loadMovies()
    }
}