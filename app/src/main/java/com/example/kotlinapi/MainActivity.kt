package com.example.kotlinapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    @SuppressLint("SetTextI18n")
    fun search(view: View) {
        val town = editTown.text.toString()
        val repository = SearchRepositoryProvider.provideSearchRepository()
        repository.searchUsers(town)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                    result ->
                Log.d("Result", "There are ${result.total_count} developers in ${town}")
                textResult.text = "There are ${result.total_count} developers in ${town}"
            }, { error ->
                textResult.text = error.localizedMessage
                error.printStackTrace()
            })
    }
}
