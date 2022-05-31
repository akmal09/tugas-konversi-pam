package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.BuildConfig
import com.example.myapplication.FfList
import com.example.myapplication.UserBio
import com.example.myapplication.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {

    private val userDetail = MutableLiveData<UserBio>()
    private val listFollowers = MutableLiveData<List<FfList>>()
    private val listFollowing = MutableLiveData<List<FfList>>()

    fun setUserUrl(loginName : String) {
        val getUrlObject = ApiConfig.getApiService().getuserUrl(BuildConfig.Authorization,loginName)
        getUrlObject.enqueue(object : Callback<UserBio>{
            override fun onResponse(call: Call<UserBio>, response: Response<UserBio>) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null) {
                        userDetail.postValue(responseBody!!)
                        setUsersFollowing(responseBody.login)
                    }
                }else{
                    Log.e("null Detail", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserBio>, t: Throwable) {
                Log.e("error detail", "onFailure: ${t.message}")
            }
        })
    }

    fun setUsersFollowing(loginName:String) {
        val getFollowingObject = ApiConfig.getApiService().getListFollwoingObject(loginName)
        getFollowingObject.enqueue(object : Callback<List<FfList>>{
            override fun onResponse(call: Call<List<FfList>>, response: Response<List<FfList>>) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody!=null){
                        val listFollowingObject = ArrayList<FfList>()
                        for(data in responseBody) {
                            val ffObject = FfList(data.login, data.avatarUrl)
                            listFollowingObject.add(ffObject)
                        }
                        listFollowing.postValue(listFollowingObject)
                    }
                }else{
                    Log.d("no successfull", "${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FfList>>, t: Throwable) {
                Log.d("FailGetData", "${t.message}")

            }
        })

    }

    fun setUsersFollowers(url:String){
        Log.d("url nya","${url}")
        val getFollowersObject = ApiConfig.getUserFollowers(url).getFollowers(url)
        getFollowersObject.enqueue(object : Callback<List<FfList>>{
            override fun onResponse(call: Call<List<FfList>>, response: Response<List<FfList>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody!=null){
                        val listFollowersObject = ArrayList<FfList>()
                        for(data in responseBody) {
                            val ffObject = FfList(data.login, data.avatarUrl)
                            listFollowersObject.add(ffObject)
                        }
                        listFollowers.postValue(listFollowersObject)
                    }
                    Log.d("berhasilfollowers","${response.body()}")
                }
                else{
                    Log.e("responseFailed","${response.message()}, ${url}")
                }
            }
            override fun onFailure(call: Call<List<FfList>>, t: Throwable) {
                Log.e("gagal","${t.message}")
            }
        })
    }

    fun getUserBio(): LiveData<UserBio>{
        return userDetail
    }

    fun getListFollowers(): LiveData<List<FfList>> {
        return listFollowers
    }

    fun getListFollowing(): LiveData<List<FfList>> {
        return listFollowing
    }
}

//fun setUsersFollowers(loginName:String) {
//    val getFollowersObject = ApiConfig.getApiService().getListFollowersObject(loginName)
//    getFollowersObject.enqueue(object : Callback<List<FfList>>{
//        override fun onResponse(call: Call<List<FfList>>, response: Response<List<FfList>>) {
//            if (response.isSuccessful){
//                val responseBody = response.body()
//                if(responseBody!=null){
//                    val listFollowersObject = ArrayList<FfList>()
//                    for(data in responseBody) {
//                        val ffObject = FfList(data.login, data.avatarUrl)
//                        listFollowersObject.add(ffObject)
//                    }
//                    listFollowers.postValue(listFollowersObject)
//                }
//            }else{
//                Log.d("no successfull", "${response.message()}")
//            }
//        }
//
//        override fun onFailure(call: Call<List<FfList>>, t: Throwable) {
//            Log.d("FailGetData", "${t.message}")
//        }
//    })
//}