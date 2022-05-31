package com.example.myapplication.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.text.FieldPosition

class SectionsPagerAdapter(activity: AppCompatActivity, private val userLogin:String, private val followersUrl:String): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment{
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ListFollowersFragment.newInstance(followersUrl)
            1 -> fragment = ListFollowingFragment.newInstance(userLogin)
        }
        return fragment as Fragment
    }
}