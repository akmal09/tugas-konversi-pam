package com.example.myapplication

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var resultAdapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
        showLoading(false)

        supportActionBar?.title = "Github User Finder"
        val layoutManager = LinearLayoutManager(this)
        binding.listUsers.layoutManager = layoutManager

        setAdapter()
        mainViewModel.getUserSearch().observe(this,{
            resultAdapter.setData(it)
            showLoading(false)
//            Log.d("list user","${it}")
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_bar, menu)

        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search_button)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.cari)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                getUserQuery(query)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun getUserQuery(query:String){
        if (query == "") {
            showLoading(false)
        }else{
            showLoading(true)
        }
        mainViewModel.setUserSearch(query)
    }

    private fun setAdapter(){
        binding.listUsers.layoutManager = LinearLayoutManager(this)
        resultAdapter = ResultAdapter()
        resultAdapter.notifyDataSetChanged()
        binding.listUsers.adapter = resultAdapter
        resultAdapter.setOnItemClick(object : ResultAdapter.OnClickItem{
            override fun onClicked(data: UserResultToLocal) {
                moveToDetail(data)
            }
        })
    }

    private fun moveToDetail(data: UserResultToLocal) {
        val move = Intent(this@MainActivity, DetailUserActivity::class.java)
        move.putExtra(DetailUserActivity.EXTRA_PERSON, data)
        startActivity(move)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

}