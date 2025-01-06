package com.example.countrysapp

import android.widget.ImageView
import coil3.ImageLoader
import coil3.load
import coil3.request.ImageRequest
import coil3.request.ImageResult
import coil3.request.target
import coil3.svg.SvgDecoder
import java.util.Locale


fun languagesToString(languages: List<Language>): String {
    var langInString: String = ""

    for(lang in languages){
        if(lang == languages.last())
            langInString += lang.name
        else
            langInString += "${lang.name}, "
    }
    return langInString
    // Или просто languages.joinToString(", ")
}

fun simpleSeparate(long: Long): String{
    val string: String = java.lang.String.format(Locale.US, "%,d", long)
    return string
}

fun separateIntegerByComma(number: Long): String {
    val numberAsString = number.toString()
    val reversedNumber = numberAsString.reversed()
    val separatedNumber = reversedNumber.chunked(3).joinToString(" ").reversed()
    return separatedNumber
}


suspend fun loadSvg(imageView: ImageView, url: String) {
    if(url.lowercase(Locale.ENGLISH).endsWith("svg")) {
        val imageLoader = ImageLoader.Builder(imageView.context).components{ //.componentRegistry {
        add(SvgDecoder.Factory())
        //add(SvgDecoder(imageView.context))
            }
            .build()
        val request = ImageRequest.Builder(imageView.context)
            .data(url)
            .target(imageView)
            .build()

        try{
            imageLoader.execute(request)
        } catch(e: Exception){
            e.message
        }

    } else {
        imageView.load(url)
    }
}