package com.gee12.peopledates.data.model

import com.gee12.peopledates.Utils

data class Group(
    val id: Int?,
    val name: String,
    val url: String
) {
    val path = Utils.parseUri(url)?.path?.apply {
        if (first().equals("/")) replaceFirst("/", "")
    }
}
