package com.mercadolibre.android.andesui.progress

import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.progress.extensions.get
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorAttrs
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorConfigurationFactory
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorDeterminateConfiguration
import com.mercadolibre.android.andesui.progress.size.AndesProgressIndicatorSize

class AndesProgressIndicator {

    fun create() {
        val progress = AndesProgressIndicatorConfigurationFactory.create(
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
}