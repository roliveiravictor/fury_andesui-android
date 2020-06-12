package com.mercadolibre.android.andesui.progress.size

/**
 * Utility class that does two things: Defines the possible sizes an [AndesProgressIndicator] can take because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * You ask me with, let's say 'SMALL', and then I'll give you a proper implementation of that size.
 *
 * @property size Possible sizes that an [AndesProgressIndicator] may take.
 */
enum class AndesProgressIndicatorSize {
    TINY,
    SMALL,
    MEDIUM,
    LARGE,
    XLARGE;

    companion object {
        fun fromString(value: String): AndesProgressIndicatorSize = valueOf(value.toUpperCase())
    }

    internal val size get() = getAndesProgressIndicatorSize()

    private fun getAndesProgressIndicatorSize(): AndesProgressIndicatorSizeInterface {
        return when (this) {
            TINY -> AndesTinyProgressIndicatorSize()
            SMALL -> AndesSmallProgressIndicatorSize()
            MEDIUM -> AndesMediumProgressIndicatorSize()
            LARGE -> AndesLargeProgressIndicatorSize()
            XLARGE -> AndesXLargeProgressIndicatorSize()
        }
    }
}
