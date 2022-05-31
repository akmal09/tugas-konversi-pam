package com.example.myapplication.fragment

import android.os.Bundle
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
import com.example.myapplication.databinding.FragmentListFollowingListBinding
import com.example.myapplication.viewmodel.DetailUserViewModel

/**
 * A fragment representing a list of Items.
 */
class ListFollowingFragment : Fragment() {

    private lateinit var binding: FragmentListFollowingListBinding
    private lateinit var detailUserViewModel: DetailUserViewModel
    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListFollowingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userLogin = arguments?.getString(ARG_USER)
        detailUserViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailUserViewModel::class.java)
//        detailUserViewModel.isloading.observe(viewLifecycleOwner,{
//            showLoading(it)
//        })
        if (userLogin != null) {
            detailUserViewModel.setUsersFollowing(userLogin)
        }
        detailUserViewModel.getListFollowing().observe(viewLifecycleOwner, {
            setFFAdapterFollowing(it)
            showLoading(false)
        })
    }

    private fun setFFAdapterFollowing(listFfObject: List<FfList>) {
        binding.listFollowing.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        binding.listFollowing.layoutManager = layoutManager
        val ffAdapter = FfAdapter(listFfObject)
        binding.listFollowing.adapter = ffAdapter
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
        const val ARG_USER = "userLogin"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(userLogin: String): ListFollowingFragment{
                val fragment = ListFollowingFragment()
                val bundle = Bundle()
                bundle.putString(ARG_USER, userLogin)
                fragment.arguments = bundle
                return fragment
            }
    }
}