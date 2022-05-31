package com.example.myapplication.api

import com.example.myapplication.FfList
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

    @GET("users/{login}")
    fun getuserUrl(
        @Header("Authorization") Authorization:String,
        @Path("login") login:String
    ): Call<UserBio>

    @GET("users/{login}/following")
    fun getListFollwoingObject(
        @Path("login") login: String?
    ): Call<List<FfList>>

    @GET
    fun getFollowers(
        @Url url: String
    ): Call<List<FfList>>
}