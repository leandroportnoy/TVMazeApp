package br.com.las.commom.extensions

import android.content.Context

/**
 * Convert Resource to String
 *
 * @param context
 * @sample R.string.lb_app_name.idToString(context)
 * @return String DEFAULT: ""
 */
fun Int.idToString(context: Context?): String = context?.getString(this) ?: ""

