package com.mercadolibre.android.andesui.progress.extensions

import com.mercadolibre.android.andesui.progress.exception.AndesProgressIndicatorConfigurationException
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorConfiguration

inline fun <reified T: AndesProgressIndicatorConfiguration> Any.get(): T {
    return try {
        this as T
    } catch (e: ClassCastException) {
        throw AndesProgressIndicatorConfigurationException("Unsupported progress indicator type.")
    }
}