package com.example.moviewatchlist.model.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviewatchlist.data.MoviesData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception

class MoviesService {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("movies")

    @Volatile private var INSTANCE: MoviesService ?= null

    fun getInstance(): MoviesService{
        return INSTANCE ?: synchronized(this){
            val instance = MoviesService()
            INSTANCE = instance
            instance
        }
    }

    fun loadMovies(moviesList: MutableLiveData<List<MoviesData>>){
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _movieList : List<MoviesData> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(MoviesData::class.java)!!
                    }
                    moviesList.postValue(_movieList)
                }catch (e:Exception){
                    println(e.message)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
    }
}