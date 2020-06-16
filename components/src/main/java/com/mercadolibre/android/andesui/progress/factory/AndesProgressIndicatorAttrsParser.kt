package com.mercadolibre.android.andesui.progress.factory

import android.content.Context
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.progress.size.AndesProgressIndicatorSize

/**
 * The data class that contains the public components of the badge.
 */
internal data class AndesProgressIndicatorAttrs(
        val size: AndesProgressIndicatorSize,
        val tint: AndesColor,
        val textColor: AndesColor,
        val label: String,
        val linear: Boolean,
        val offset: Int = 0,
        // Should create something similar to AndesColor (@ColorRes) { -> AndesDrawable (@DrawableRes) },
        // but oriented to drawable resources?
        @DrawableRes val thumbnail: Int
)

/**
 * This object parse the attribute set and return an instance of AndesBadgeAttrs to be used by AndesBadge
 */
internal object AndesProgressIndicatorAttrsParser {

    fun parse(context: Context, attr: AttributeSet?): AndesProgressIndicatorAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesBadgeDot)

        return AndesProgressIndicatorAttrs(
                AndesProgressIndicatorSize.SMALL,
                AndesColor(R.color.andes_accent_color),
                AndesColor(R.color.andes_accent_color),
                "label",
                false,
                0,
                R.drawable.andes_border
        ).also { typedArray.recycle() }
    }
}
