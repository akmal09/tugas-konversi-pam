package com.example.myapplication.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.FfList
import com.example.myapplication.databinding.UserListsBinding

class FfAdapter(private val listFfObject : List<FfList>): RecyclerView.Adapter<FfAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val bindingLayer = UserListsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(bindingLayer)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (login, avaUrl) = listFfObject[position]
        holder.apply {
            Glide.with(itemView.context)
                .load(avaUrl)
                .circleCrop()
                .into(binding.avaPhoto)
            binding.name.text = login
        }
    }

    override fun getItemCount(): Int =listFfObject.size

    class ListViewHolder(var binding : UserListsBinding) : RecyclerView.ViewHolder(binding.root)

}