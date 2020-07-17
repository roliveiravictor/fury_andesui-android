package com.mercadolibre.android.andesui.progress

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.checkbox.AndesCheckbox
import com.mercadolibre.android.andesui.checkbox.align.AndesCheckboxAlign
import com.mercadolibre.android.andesui.checkbox.factory.AndesCheckboxAttrs
import com.mercadolibre.android.andesui.checkbox.factory.AndesCheckboxConfigurationFactory
import com.mercadolibre.android.andesui.checkbox.status.AndesCheckboxStatus
import com.mercadolibre.android.andesui.checkbox.type.AndesCheckboxType
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.progress.extensions.get
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorAttrs
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorAttrsParser
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorConfiguration
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorConfigurationFactory
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorDeterminateConfiguration
import com.mercadolibre.android.andesui.progress.size.AndesProgressIndicatorSize

class AndesProgressIndicator : ConstraintLayout {

    private lateinit var andesProgressIndicatorAttrs: AndesProgressIndicatorAttrs

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
            context: Context,
            size: AndesProgressIndicatorSize,
            tint: AndesColor,
            textColor: AndesColor,
            label: String,
            linear: Boolean,
            offset: Int = 0,
            @DrawableRes thumbnail: Int
    ) : super(context) {
        initAttrs(size, tint, textColor, label, linear, offset, thumbnail)
    }


    /*
    * Test Method - Will be removed
    * */
    private fun create() {
        val progress = AndesProgressIndicatorConfigurationFactory.create(context,
                AndesProgressIndicatorAttrs(
                        AndesProgressIndicatorSize.SMALL,
                        AndesColor(R.color.andes_accent_color),
                        AndesColor(R.color.andes_accent_color),
                        "label",
                        false,
                        0,
                        R.drawable.andes_border
                )
        ).get<AndesProgressIndicatorDeterminateConfiguration>()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        andesProgressIndicatorAttrs = AndesProgressIndicatorAttrsParser.parse(context, attrs)
        setupComponents(
                AndesProgressIndicatorConfigurationFactory.create(
                        context,
                        andesProgressIndicatorAttrs
                )
        )
    }

    private fun initAttrs(
            size: AndesProgressIndicatorSize,
            tint: AndesColor,
            textColor: AndesColor,
            label: String,
            linear: Boolean,
            offset: Int = 0,
            @DrawableRes thumbnail: Int
    ) {
        andesProgressIndicatorAttrs = AndesProgressIndicatorAttrs(
                size,
                tint,
                textColor,
                label,
                linear,
                offset,
                thumbnail
        )
        setupComponents(
                AndesProgressIndicatorConfigurationFactory.create(
                        context,
                        andesProgressIndicatorAttrs
                )
        )
    }

    private fun setupComponents(config: AndesProgressIndicatorConfiguration) {

    }
}