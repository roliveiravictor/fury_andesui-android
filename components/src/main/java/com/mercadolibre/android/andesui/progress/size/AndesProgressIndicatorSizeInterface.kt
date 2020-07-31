package com.mercadolibre.android.andesui.progress.size

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all size related properties that an [AndesProgressIndicator] needs to be drawn properly.
 * Those properties change depending on the size of the progress indicator.
 */
internal interface AndesProgressIndicatorSizeInterface {
    /**
     * Returns a [Float] representing the text size to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the text size to be used.
     */
    fun textSize(context: Context): Float

    /**
     * Returns a [Float] representing the height size to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the height size to be used.
     */
    fun height(context: Context): Float

    /**
     * Returns an [Int] representing the margin to be used.
     *
     * @param context needed for accessing some resources.
     * @return an [Int] representing the margin to be used.
     */
    fun margin(context: Context): Int
}

/**
 * Implementation of [AndesProgressIndicatorSizeInterface] that returns the required data but personalized for the Large Size,
 * according to Andes specifications.
 *
 */
internal class AndesTinyProgressIndicatorSize : AndesProgressIndicatorSizeInterface {
    override fun textSize(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_medium_text_size)
    override fun height(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_tiny_height)
    override fun margin(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_medium_margin).toInt()
}

/**
 * Implementation of [AndesProgressIndicatorSizeInterface] that returns the required data but personalized for the Small Size,
 * according to Andes specifications.
 */
internal class AndesSmallProgressIndicatorSize : AndesProgressIndicatorSizeInterface {
    override fun textSize(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_medium_text_size)
    override fun height(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_small_height)
    override fun margin(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_medium_margin).toInt()
}

/**
* Implementation of [AndesProgressIndicatorSizeInterface] that returns the required data but personalized for the Small Size,
* according to Andes specifications.
*/
internal class AndesMediumProgressIndicatorSize : AndesProgressIndicatorSizeInterface {
    override fun textSize(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_medium_text_size)
    override fun height(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_medium_height)
    override fun margin(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_medium_margin).toInt()
}

/**
 * Implementation of [AndesProgressIndicatorSizeInterface] that returns the required data but personalized for the Small Size,
 * according to Andes specifications.
 */
internal class AndesLargeProgressIndicatorSize : AndesProgressIndicatorSizeInterface {
    override fun textSize(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_large_text_size)
    override fun height(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_large_height)
    override fun margin(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_large_margin).toInt()
}

/**
 * Implementation of [AndesProgressIndicatorSizeInterface] that returns the required data but personalized for the Small Size,
 * according to Andes specifications.
 */
internal class AndesXLargeProgressIndicatorSize : AndesProgressIndicatorSizeInterface {
    override fun textSize(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_xlarge_text_size)
    override fun height(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_xlarge_height)
    override fun margin(context: Context) = context.resources.getDimension(R.dimen.andes_progress_indicator_xlarge_margin).toInt()
}
