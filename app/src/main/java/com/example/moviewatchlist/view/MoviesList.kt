package com.example.moviewatchlist.view

import android.content.ContentValues
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviewatchlist.R
import com.example.moviewatchlist.data.MoviesData
import com.example.moviewatchlist.databinding.FragmentMoviesListBinding
import com.example.moviewatchlist.view.adapter.MyAdapter
import com.example.moviewatchlist.viewmodel.MovieViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private lateinit var viewModel: MovieViewModel
private lateinit var movieRecyclerView: RecyclerView
private lateinit var adapter: MyAdapter

class MoviesList : Fragment(), MyAdapter.OnItemClickListener {
    private lateinit var binding: FragmentMoviesListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentMoviesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieRecyclerView = view.findViewById(R.id.recyclerView)
        movieRecyclerView.layoutManager = LinearLayoutManager(context)
        movieRecyclerView.setHasFixedSize(true)
        adapter = MyAdapter(arrayListOf(), this)
        movieRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.allMovies.observe(viewLifecycleOwner, Observer {
            adapter.updateMoviesList(it)
        })
    }
       override fun onItemClick(moviesData: String?) {
        val bundle = Bundle()
        bundle.putString("movies",moviesData)
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, detailFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
}