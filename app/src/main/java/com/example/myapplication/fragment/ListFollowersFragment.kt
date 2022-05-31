package com.example.myapplication.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.FfList
import com.example.myapplication.R
import com.example.myapplication.ResultAdapter
import com.example.myapplication.databinding.ActivityDetailUserBinding.inflate
import com.example.myapplication.databinding.FragmentListFollowersListBinding
import com.example.myapplication.viewmodel.DetailUserViewModel

/**
 * A fragment representing a list of Items.
 */
class ListFollowersFragment : Fragment() {

    private lateinit var binding:FragmentListFollowersListBinding
    private lateinit var detailUserViewModel: DetailUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListFollowersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val followersUrl = arguments?.getString(ARG_USER)
        detailUserViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            DetailUserViewModel::class.java)
        if (followersUrl != null) {
            detailUserViewModel.setUsersFollowers(followersUrl)
        }
        detailUserViewModel.getListFollowers().observe(viewLifecycleOwner,{
            setAdapter(it)
            showLoading(false)
        })
    }

    private fun setAdapter(listObject: List<FfList>) {
        binding.listFollowers.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        binding.listFollowers.layoutManager = layoutManager
        val followersAdapter = FfAdapter(listObject)
        binding.listFollowers.adapter = followersAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_USER = "followersUrl"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(followersUrl: String?):ListFollowersFragment{
            val fragment = ListFollowersFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USER, followersUrl)
            fragment.arguments = bundle
            return fragment
        }

    }
}