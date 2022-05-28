package com.example.myapplication.api

import com.example.myapplication.ResponseApi
import com.example.myapplication.UserBio
import com.example.myapplication.UserResult
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    fun getSearchQuery(
        @Header("Authorization") Authorization:String,
        @Query("q") login: String
    ): Call<ResponseApi>

    @GET("users/{login}/following")
    fun getUsersFollowing(
        @Path("login") login: String?
    ): Call<List<UserResult>>

    @GET("users/{login}/followers")
    fun getUserFollowers(
        @Path("login") login:String?
    ): Call<List<UserResult>>

    @GET("users/{login}")
    fun getuserUrl(
        @Path("login") login:String
    ): Call<UserBio>
}