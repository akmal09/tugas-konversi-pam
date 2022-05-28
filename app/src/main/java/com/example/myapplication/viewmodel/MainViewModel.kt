package com.example.myapplication.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.*
import com.example.myapplication.api.ApiConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainViewModel: ViewModel() {
    private val queryResults = MutableLiveData<List<UserResultToLocal>>()

    fun setUserSearch(query:String) {
        val client = ApiConfig.getApiService().getSearchQuery(BuildConfig.Authorization, query)
//      Proses asynchronus menggunakan retrofit
        client.enqueue(object : Callback<ResponseApi> {
            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                if (response.isSuccessful) {
                    val listUserResultToLocal = ArrayList<UserResultToLocal>()
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val items = responseBody.items
                        for(data in items){
                            val userResultLocal = UserResultToLocal(data.id,data.login,data.followersUrl,
                                data.url,data.avatarUrl
                            )
                            listUserResultToLocal.add(userResultLocal)
                        }
                        queryResults.postValue(listUserResultToLocal)
                    }
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getUserSearch() : LiveData<List<UserResultToLocal>>{
        return queryResults
    }
}