package com.gee12.peopledates.network

import android.graphics.Bitmap

interface IBitmapLoader {
    fun load(url: String): Bitmap
}