package com.bodiart.hardconvertrecyclerview

import android.graphics.Bitmap

data class Image(
    val url: String,
    var imageBitmap: Bitmap? = null
)