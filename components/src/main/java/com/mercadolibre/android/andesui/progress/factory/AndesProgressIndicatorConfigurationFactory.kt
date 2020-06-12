package com.mercadolibre.android.andesui.progress.factory

import android.content.Context
import android.support.annotation.DrawableRes
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.progress.exception.AndesProgressIndicatorConfigurationException
import com.mercadolibre.android.andesui.progress.size.AndesProgressIndicatorSize

internal abstract class AndesProgressIndicatorConfiguration

internal data class AndesProgressIndicatorDeterminateConfiguration(
        val size: AndesProgressIndicatorSize,
        val tint: AndesColor,
        val textColor: AndesColor,
        val label: String,
        val linear: Boolean,
        val offset: Int,
        // Should create something similar to AndesColor (@ColorRes) { -> AndesDrawable (@DrawableRes) },
        // but oriented to drawable resources?
        @DrawableRes val thumbnail: Int
) : AndesProgressIndicatorConfiguration()

internal data class AndesProgressIndicatorIndeterminateConfiguration(
        val size: AndesProgressIndicatorSize,
        val tint: AndesColor,
        val textColor: AndesColor,
        val label: String,
        // Should create something similar to AndesColor (@ColorRes) { -> AndesDrawable (@DrawableRes) },
        // but oriented to drawable resources?
        @DrawableRes val thumbnail: Int
) : AndesProgressIndicatorConfiguration()

internal object AndesProgressIndicatorConfigurationFactory {

    fun create(attrs: AndesProgressIndicatorAttrs): AndesProgressIndicatorConfiguration {
        validateAttributes(attrs)
        return configure(attrs)
    }

    private fun configure(attrs: AndesProgressIndicatorAttrs): AndesProgressIndicatorConfiguration {
        return if (attrs.offset != 0) {
            AndesProgressIndicatorDeterminateConfiguration(
                    size = resolveSize(),
                    tint = resolveTint(),
                    textColor = resolveTextColor(),
                    label = resolveLabel(),
                    linear = resolveLinear(),
                    offset = resolverOffset(),
                    thumbnail = resolveThumbnail()
            )
        } else {
            AndesProgressIndicatorIndeterminateConfiguration(
                    size = resolveSize(),
                    tint = resolveTint(),
                    textColor = resolveTextColor(),
                    label = resolveLabel(),
                    thumbnail = resolveThumbnail()
            )
        }
    }

    private fun validateAttributes(attrs: AndesProgressIndicatorAttrs) {
        if(attrs.linear && attrs.offset == 0) {
            throw AndesProgressIndicatorConfigurationException("Attribute linear is not allowed to be settle without offset.")
        }
    }

    private fun resolveLinear(): Boolean {
        return false
    }

    private fun resolverOffset(): Int {
        return 0
    }

    private fun resolveSize(): AndesProgressIndicatorSize {
        return AndesProgressIndicatorSize.SMALL
    }

    private fun resolveTint(): AndesColor {
        return AndesColor(R.color.andes_accent_color)
    }

    private fun resolveTextColor(): AndesColor {
        return AndesColor(R.color.andes_accent_color)
    }

    private fun resolveLabel(): String {
        return ""
    }

    @DrawableRes
    private fun resolveThumbnail(): Int {
        return R.drawable.andes_border
    }
}
