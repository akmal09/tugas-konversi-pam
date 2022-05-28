package com.example.myapplication

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.RemoteViews
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.UserListsBinding

class ResultAdapter: RecyclerView.Adapter<ResultAdapter.ListViewHolder>() {

    private lateinit var onClickItem: OnClickItem
    var mResultData = ArrayList<UserResultToLocal>()

    fun setData(items: List<UserResultToLocal>) {
        mResultData.clear()
        mResultData.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClick(onClickItem: OnClickItem) {
        this.onClickItem = onClickItem
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val bindingLayer = UserListsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(bindingLayer)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mResultData[position])
    }

    override fun getItemCount(): Int = mResultData.size

    inner class ListViewHolder(private val binding: UserListsBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(userResultToLocal: UserResultToLocal){
            with(binding){
                Glide.with(itemView.context)
                    .load(userResultToLocal.avatarUrl)
                    .into(avaPhoto)
                name.text = userResultToLocal.login
                itemView.setOnClickListener{
                    onClickItem.onClicked(userResultToLocal)
                }
            }
        }
    }

    interface OnClickItem{
        fun onClicked(data : UserResultToLocal)
    }
}