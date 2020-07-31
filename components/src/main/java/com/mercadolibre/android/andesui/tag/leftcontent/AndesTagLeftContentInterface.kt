package com.mercadolibre.android.andesui.tag.leftcontent

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.utils.buildCircleBitmap
import com.mercadolibre.android.andesui.utils.buildColoredBitmapDrawable
import com.mercadolibre.android.andesui.utils.validateColor

internal sealed class AndesTagLeftContentInterface {
    abstract fun leftMargin(context: Context): Int
    abstract fun rightMargin(context: Context): Int
    abstract fun size(context: Context): Int
    abstract fun border(context: Context): Float
    abstract fun view(context: Context, leftContent: LeftContent): View
}

internal object AndesTagLeftContentNone : AndesTagLeftContentInterface() {
    override fun leftMargin(context: Context): Int = 0
    override fun rightMargin(context: Context): Int = 0
    override fun size(context: Context): Int = 0
    override fun border(context: Context): Float = 0f
    override fun view(context: Context, leftContent: LeftContent): View = View(context)
}

internal object AndesTagLeftContentDot : AndesTagLeftContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
    override fun size(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt()
    override fun border(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
    override fun view(context: Context, leftContent: LeftContent): View {
        // Valid colors
        var isValid = validateColor(leftContent.dot!!.backgroundColor)
        if (isValid && !leftContent.dot!!.textColor.isNullOrEmpty()) {
            isValid = isValid && validateColor(leftContent.dot!!.textColor!!)
        }
        if (!isValid) {
            throw IllegalStateException("Invalid color, it is necessary to pass it in hexadecimal.")
        }
        // Valid text
        if (!leftContent.dot!!.text.isNullOrEmpty() && leftContent.dot!!.text!!.length > 2) {
            leftContent.dot!!.text = leftContent.dot!!.text!!.substring(0, 2)
        }
        // Create view
        if (leftContent.dot!!.text.isNullOrEmpty()) {
            val view = View(context)
            val shape = GradientDrawable()
            shape.cornerRadius = border(context)
            shape.setColor(Color.parseColor(leftContent.dot!!.backgroundColor))
            view.background = shape
            return view
        } else {
            val textView = TextView(context)
            textView.text = leftContent.dot!!.text
            textView.gravity = Gravity.CENTER
            val shape = GradientDrawable()
            shape.cornerRadius = border(context)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.andes_tag_small_text_size))
            if (!leftContent.dot!!.textColor.isNullOrEmpty()) {
                textView.setTextColor(Color.parseColor(leftContent.dot!!.textColor))
            }
            shape.setColor(Color.parseColor(leftContent.dot!!.backgroundColor))
            textView.background = shape
            return textView
        }
    }
}

internal object AndesTagLeftContentIcon : AndesTagLeftContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
    override fun size(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt()
    override fun border(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
    override fun view(context: Context, leftContent: LeftContent): View {
        // Valid color
        if (!leftContent.icon!!.iconColor.isNullOrEmpty() && !validateColor(leftContent.icon!!.iconColor!!)) {
            throw IllegalStateException("Invalid color, it is necessary to pass it in hexadecimal.")
        }
        // Valid if i have path or drawable
        if (leftContent.icon!!.path.isNullOrEmpty() && leftContent.icon!!.icon == null) {
            throw IllegalStateException("A drawable or IconProvider path is required.")
        }

        val imageView = ImageView(context)
        val shape = GradientDrawable()
        shape.cornerRadius = border(context)
        shape.setColor(Color.parseColor(leftContent.icon!!.backgroundColor))
        imageView.background = shape

        val bitmapDrawable = if (!leftContent.icon!!.path.isNullOrEmpty()) {
            IconProvider(context).loadIcon(leftContent.icon!!.path!!) as BitmapDrawable
        } else {
            val padding = context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
            imageView.setPadding(padding, padding, padding, padding)
            leftContent.icon!!.icon as BitmapDrawable
        }

        if (leftContent.icon!!.iconColor.isNullOrEmpty()) {
            leftContent.icon!!.iconColor = "#000000"
        }
        val icon = buildColoredBitmapDrawable(
                bitmapDrawable,
                context,
                dstWidth = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt(),
                dstHeight = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt(),
                color = Color.parseColor(leftContent.icon!!.iconColor)
        )

        imageView.setImageDrawable(icon)
        return imageView
    }
}

internal object AndesTagLeftContentImage : AndesTagLeftContentInterface() {
    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
    override fun size(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt()
    override fun border(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
    override fun view(context: Context, leftContent: LeftContent): View {
        // Create avatar
        val icon = buildCircleBitmap(leftContent.image!!.avatar, size(context), size(context))
        if (icon != null) {
            leftContent.image!!.avatar = icon
        }
        val imageView = ImageView(context)
        imageView.setImageBitmap(leftContent.image!!.avatar)
        return imageView
    }
}
