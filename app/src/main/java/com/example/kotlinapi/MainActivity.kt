package com.example.kotlinapi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun search(view: View) {
        val town = editTown.text.toString()
        val repository = SearchRepositoryProvider.provideSearchRepository()
        repository.searchUsers(town)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                    result ->
                val userList:MutableList<User> = mutableListOf()
                for (user in result.items){
                    userList.add(User(user.login, user.html_url, user.avatar_url))
                }
                val message = getString(R.string.find_message, result.total_count, town, result.items.size)
                Log.d(getString(R.string.result_tag), message)
                textResult.text = message
                val myAdapter = UserAdapter(userList.toList(), object : UserAdapter.Callback {
                    override fun onItemClicked(item: User) {
                        val uris = Uri.parse(item.html_url)
                        val intents = Intent(Intent.ACTION_VIEW, uris)
                        view.context.startActivity(intents)
                     }
                })
                list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
                list.adapter = myAdapter
            }, { error ->
                textResult.text = error.localizedMessage
                error.printStackTrace()
            })
    }
}
