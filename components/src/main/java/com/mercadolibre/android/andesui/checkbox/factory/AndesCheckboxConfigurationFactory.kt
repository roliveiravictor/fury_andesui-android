package com.mercadolibre.android.andesui.checkbox.factory

import com.mercadolibre.android.andesui.checkbox.align.AndesCheckboxAlign
import com.mercadolibre.android.andesui.checkbox.status.AndesCheckboxStatus
import com.mercadolibre.android.andesui.checkbox.type.AndesCheckboxType

internal data class AndesCheckboxConfiguration(
    val text: String?,
    val textSize: Int,
    val align: AndesCheckboxAlign,
    val status: AndesCheckboxStatus,
    val type: AndesCheckboxType
)

internal object AndesCheckboxConfigurationFactory {

    fun create(andesCheckboxAttrs: AndesCheckboxAttrs): AndesCheckboxConfiguration {
        return with(andesCheckboxAttrs) {
            val validatedStatus = if (andesCheckboxAttrs.andesCheckboxType == AndesCheckboxType.ERROR) {
                AndesCheckboxStatus.UNSELECTED
            } else {
                andesCheckboxAttrs.andesCheckboxStatus
            }
            AndesCheckboxConfiguration(
                    text = andesCheckboxText,
                    textSize = resolveTextSize(),
                    align = andesCheckboxAttrs.andesCheckboxAlign,
                    status = validatedStatus,
                    type = andesCheckboxAttrs.andesCheckboxType
            )
        }
    }

    private fun resolveTextSize() = 16
}
