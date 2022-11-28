package br.com.las.commom.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat

/**
 * Convert Resource to Boolean
 *
 * @param context
 * @sample R.bool.is_tablet.idToBoolean(context)
 * @return true or false, DEFAULT: false
 */
fun Int.idToBoolean(context: Context?): Boolean =
    context?.resources?.getBoolean(this) ?: false

/**
 * Convert Resource to String
 *
 * @param context
 * @sample R.string.lb_app_name.idToString(context)
 * @return String DEFAULT: ""
 */
fun Int.idToString(context: Context?): String = context?.getString(this) ?: ""

/**
 * Convert Resource to Color
 *
 * @param context
 * @sample R.color.red.idToColor(context)
 * @return @ColorInt DEFAULT: WHITE
 */
fun Int.idToColor(context: Context?): Int = context?.let {
    ContextCompat.getColor(it, this)
} ?: Color.WHITE

/**
 * Convert Resource to Drawable
 *
 * @param context
 * @sample R.drawable.ic_back.idToDrawable(context)
 * @return Resource Drawable, DEFAULT: null
 */
fun Int.idToDrawable(context: Context?): Drawable? = context?.let {
    ContextCompat.getDrawable(it, this)
}

/**
 * Covert Vector Drawable to Bitmap DEFAULT: null
 *
 * @param context
 * @return Bitmap
 */
fun Int.idVectorToBitmap(context: Context?): Bitmap? {
    var drawable = this.idToDrawable(context)
    drawable = drawable?.let {
        DrawableCompat.wrap(it).mutate()
    }
    return drawable?.let {
        val bitmap = Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        it.setBounds(0, 0, canvas.width, canvas.height)
        it.draw(canvas)
        bitmap
    }
}

/**
 * Convert Resource to Dimen Int DEFAULT VALUE: 1px
 *
 * @param context
 * @sample R.dimen.appMargin.idToDimen(context)
 * @return Int - Size
 */
fun Int.idToDimen(context: Context?): Int = context?.resources?.getDimensionPixelSize(this) ?: 1

/**
 * Convert Resource Font ID to Typeface
 *
 * @param context
 * @sample R.font.poppins.idToFont(context)
 * @return Typeface
 */
fun Int.idToFont(context: Context?): Typeface? = context?.let {
    ResourcesCompat.getFont(it, this)
}

/**
 * Convert Resource Layout ID to View, used in Activities
 *
 * @param inflater
 * @param container
 * @param attachToRoot
 * @sample R.font.recyclerview_b.idToLayout(context)
 * @return View - Optional if it fails
 */
fun Int.idToLayout(inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): View? =
    inflater.inflate(this, container, attachToRoot)

/**
 * Convert Resource Layout ID to View, used in Components
 *
 * @param context
 * @param container
 * @return View -
 */
fun Int.idToLayout(context: Context, container: ViewGroup?): View =
    LayoutInflater.from(context).inflate(this, container)
