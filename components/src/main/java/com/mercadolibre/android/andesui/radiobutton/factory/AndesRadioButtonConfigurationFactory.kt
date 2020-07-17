package com.mercadolibre.android.andesui.radiobutton.factory

import com.mercadolibre.android.andesui.radiobutton.align.AndesRadioButtonAlign
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadioButtonStatus
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadioButtonType

internal data class AndesRadioButtonConfiguration(
    val text: String?,
    val textSize: Int,
    val align: AndesRadioButtonAlign,
    val status: AndesRadioButtonStatus,
    val type: AndesRadioButtonType
)

internal object AndesRadioButtonConfigurationFactory {

    fun create(andesRadioButtonAttrs: AndesRadioButtonAttrs): AndesRadioButtonConfiguration {
        return with(andesRadioButtonAttrs) {
            val validatedStatus = if (andesRadioButtonAttrs.andesRadioButtonType == AndesRadioButtonType.ERROR) {
                AndesRadioButtonStatus.UNSELECTED
            } else {
                andesRadioButtonAttrs.andesRadioButtonStatus
            }
            AndesRadioButtonConfiguration(
                    text = andesRadioButtonText,
                    textSize = resolveTextSize(),
                    align = andesRadioButtonAttrs.andesRadioButtonAlign,
                    status = validatedStatus,
                    type = andesRadioButtonAttrs.andesRadioButtonType
            )
        }
    }

    private fun resolveTextSize() = 16
}
