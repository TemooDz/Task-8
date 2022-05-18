package com.example.hw8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var adapter: UsersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersRecyclerView = findViewById(R.id.usersRecyclerView)
        init()
    }

    private fun init() {
        usersRecyclerView.layoutManager = LinearLayoutManager(this)

        ApiClient.getService().getUsers().enqueue(object : Callback<UsersDTO> {
            override fun onResponse(call: Call<UsersDTO>, response: Response<UsersDTO>) {
                if (response.isSuccessful && response.body()?.data != null)
                    adapter = UsersRecyclerAdapter(response.body()?.data ?: emptyList())
                usersRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<UsersDTO>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
