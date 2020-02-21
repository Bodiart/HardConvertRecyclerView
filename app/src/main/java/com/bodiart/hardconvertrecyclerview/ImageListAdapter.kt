package com.bodiart.hardconvertrecyclerview

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bodiart.library.HardConvertRecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.coroutines.Dispatchers

class ImageListAdapter: HardConvertRecyclerView<Image>(R.layout.list_item_images) {

    override fun hardConvert(helper: BaseViewHolder, item: Image) {
        if (item.imageBitmap == null)
            loadImage(helper, item)
        else
            helper.setImageBitmap(R.id.image_iv, item.imageBitmap)
    }

    private fun loadImage(helper: BaseViewHolder, item: Image) {
        Glide.with(mContext)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(item.url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    makeItemAsyncOperation(helper, {
                        item.imageBitmap =  BitmapUtils.replaceTransparentPixelsWithGreen(resource)
                    }, Dispatchers.Default)
                }
            })
    }
}