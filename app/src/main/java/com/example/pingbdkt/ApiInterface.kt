package com.example.pingbdkt

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/users")
    fun getData():Call<List<UsersItem>>


    @GET("/courses")
    fun getCourse():Call<List<CoursesItem>>
}