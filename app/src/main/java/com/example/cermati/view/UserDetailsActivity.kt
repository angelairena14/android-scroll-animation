package com.example.cermati.view

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.cermati.R
import com.example.cermati.model.UserModel
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.list_users.view.*

class UserDetailsActivity : AppCompatActivity() {
    companion object{
        var model : UserModel? = null
    }

    fun bindDataToComponent(){
        Glide.with(this).asDrawable()
            .load(model?.urlPic)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBarImg.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBarImg.visibility = View.GONE
                    return false
                }

            })
            .into(image_user_pic)
        text_username.text = model?.username
        text_repo.text = model?.repoName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        ic_arrow_back.setOnClickListener { finish() }
        bindDataToComponent()
    }
}
