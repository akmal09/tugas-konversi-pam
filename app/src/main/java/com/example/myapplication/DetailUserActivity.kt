package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import com.example.myapplication.databinding.ActivityDetailUserBinding
import com.example.myapplication.viewmodel.DetailUserViewModel

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailUserBinding
    private lateinit var detailUserViewModel: DetailUserViewModel
    private var followersUrl: String = ""
    private var reposUrl: String = ""

    companion object{
        const val EXTRA_PERSON = "extra_person"
        @StringRes
        private val JUDUL_TAB = intArrayOf(
            R.string.tab_followers,
            R.string.tab_following
        )
        private val TAG = DetailUserActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_detail_user)

        val userSearch = intent.getParcelableExtra<UserResultToLocal>(EXTRA_PERSON) as UserResultToLocal
        Log.d("userdetail", "$userSearch")
    }
}