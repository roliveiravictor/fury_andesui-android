package com.mercadolibre.android.andesui.thumbnail.state

/**
 * Utility class that does two things: Defines the possible states an [AndesThumbnail] can take because it's an enum,
 * as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * You ask me with, let's say 'IDLE', and then I'll give you a proper implementation of that state.
 *
 * @property state Possible states that an [AndesThumbnail] may take.
 */
enum class AndesThumbnailState {
    DISABLED,
    ENABLED;

    companion object {
        fun fromString(value: String): AndesThumbnailState = valueOf(value.toUpperCase())
    }

    internal val state get() = getAndesThumbnailState()

        private fun getAndesThumbnailState(): AndesThumbnailStateInterface {
            return when (this) {
                DISABLED -> AndesDisabledThumbnailState()
                ENABLED -> AndesEnabledThumbnailState()
            }
        }
    }
