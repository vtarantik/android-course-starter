package com.tarantik.android_course_starter.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tarantik.android_course_starter.MoviesApplication
import cz.tarantik.android_course_starter.R
import cz.tarantik.android_course_starter.databinding.FragmentMoviesListBinding
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {
    private var _binding: FragmentMoviesListBinding? = null
    val binding get() = _binding!!

    private val viewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory((activity?.application as MoviesApplication).database.popularMoviesDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesAdapter = MoviesListAdapter()


       binding.rvMovies.apply {
           val orientation = activity?.resources?.configuration?.orientation

            layoutManager = if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LinearLayoutManager(requireContext())
            } else {
                GridLayoutManager(requireContext(),2)
            }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}