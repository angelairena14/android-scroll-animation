package com.example.cermati.adapter

import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.cermati.R
import com.example.cermati.model.UserModel
import com.example.cermati.model.githubusersresponse.Item
import com.example.cermati.view.UserDetailsActivity
import kotlinx.android.synthetic.main.list_users.view.*


class ListUsersAdapter : RecyclerView.Adapter<ListUsersHolder>() {
    var d = ArrayList<Item>()
    var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUsersHolder {
        return ListUsersHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    com.example.cermati.R.layout.list_users,
                    parent,
                    false
                )
        )
    }

    fun updateList(filterData : ArrayList<Item>){
        d = ArrayList()
        d.addAll(filterData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return d.size
    }

    override fun onBindViewHolder(holder: ListUsersHolder, position: Int) {
        if(d.isNotEmpty()){
            holder.itemView.apply {
                Glide.with(context).clear(this)
                Glide.get(context).clearMemory()
                Glide.with(context)
                    .load(d[position].avatar_url)
                    .dontAnimate()
                    .placeholder(R.drawable.ic_launcher_background)
                    .override(200,200)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                    .centerCrop()
                    .priority(Priority.IMMEDIATE)
                    .into(imgUsers)
                    .clearOnDetach()
                tvUsername.text = d[position].login
                this.setOnClickListener {
                    UserDetailsActivity.model = UserModel(
                        d[position].login,
                        d[position].avatar_url,
                        d[position].repos_url
                    )
                    holder.itemView.context.startActivity(Intent(context,UserDetailsActivity::class.java))
                }
            }
        }
    }
}
class ListUsersHolder(itemView: View) : RecyclerView.ViewHolder(itemView)