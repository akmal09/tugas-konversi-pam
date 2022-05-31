package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityDetailUserBinding
import com.example.myapplication.fragment.SectionsPagerAdapter
import com.example.myapplication.viewmodel.DetailUserViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailUserBinding
    private lateinit var detailUserViewModel: DetailUserViewModel
//    private var followersUrl: String = ""
//    private var reposUrl: String = ""

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
        setContentView(binding.root)

        val userSearch = intent.getParcelableExtra<UserResultToLocal>(EXTRA_PERSON) as UserResultToLocal
//        Log.d("userdetail", "$userSearch")

        detailUserViewModel = ViewModelProvider(this@DetailUserActivity).get(DetailUserViewModel::class.java)
        detailUserViewModel.setUserUrl(userSearch.login)

        detailUserViewModel.getUserBio().observe(this,{
            binding.apply {
                Glide.with(this@DetailUserActivity)
                    .load(it.avatarUrl)
                    .into(binding.avatar)
                login.text = it.login
                name.text = it.name
                location.text = it.location
                company.text = it.company
                followers.text = "${it.followers}\nFollowers"
                following.text = "${it.following}\nFollowing"
                repository.text = "${it.publicRepos}\nFollowing"
            }
        })

        val sectionsPagerAdapter = SectionsPagerAdapter(this, userSearch.login,userSearch.followersUrl)
        binding.fragment.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.fragment){tab, position ->
            tab.text = resources.getString(JUDUL_TAB[position])
        }.attach()
    }
}