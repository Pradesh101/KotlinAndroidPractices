package com.pradesh.bhaktapursales.utlis

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pradesh.bhaktapursales.ui.R
import java.io.IOException


class GlideLoader(val context: Context) {

    fun loadUserPicture(image: Any, imageView: ImageView){
        try{
            //load the user image in the Imageview
            Glide
                .with(context)
                .load(image)//URI of the image
                .centerCrop()//scale type of the image
                .placeholder(R.drawable.ic_user_placeholder) //a defualt place holder if the image is failed to load
                .into(imageView) //the view in which the image will be loaded
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
}