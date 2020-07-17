package com.mercadolibre.android.andesui.thumbnail.type

/**
 * Utility class that does two things: Defines the possible styles an [AndesThumbnail] can take because it's an enum,
 * as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * You ask me with, let's say 'COLOR', and then I'll give you a proper implementation of that style.
 *
 * @property type Possible styles that an [AndesThumbnail] may take.
 */
enum class AndesThumbnailType {
    ICON;

    companion object {
        fun fromString(value: String): AndesThumbnailType = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesThumbnailType()

    private fun getAndesThumbnailType(): AndesThumbnailTypeInterface {
        return when (this) {
            ICON -> AndesIconThumbnailType()
        }
    }
}
