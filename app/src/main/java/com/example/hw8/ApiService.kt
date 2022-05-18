package com.example.hw8

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    fun getUsers(): Call<UsersDTO>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<UserDTO>
}
