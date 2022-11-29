package br.com.las.commom.extensions

import androidx.core.text.HtmlCompat

fun String.stripHtmlOut(): String =
    HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
        .replace("\\s+$".toRegex(), "")