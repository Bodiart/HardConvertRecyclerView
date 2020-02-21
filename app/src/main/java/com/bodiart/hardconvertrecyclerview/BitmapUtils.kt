package com.bodiart.hardconvertrecyclerview

import android.graphics.Bitmap
import android.graphics.Color

class BitmapUtils {
    companion object{

        fun replaceTransparentPixelsWithGreen(bitmap: Bitmap): Bitmap {
            for (i in 0 until bitmap.width){
                for (j in 0 until bitmap.height){
                    if (Color.alpha(bitmap.getPixel(i, j)) != 255)
                        bitmap.setPixel(i, j, Color.GREEN)
                }
            }

            return bitmap
        }

    }
}