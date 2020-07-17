package com.mercadolibre.android.andesui.thumbnail.size

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all size related properties that an [AndesThumbnail] needs to be drawn properly.
 * Those properties change depending on the size of the thumbnail.
 */
internal interface AndesThumbnailSizeInterface {
    fun diameter(context: Context): Float
    fun iconSize(context: Context): Float
    fun radiusSize(context: Context): Float
}

/**
 * Implementation of [AndesThumbnailSizeInterface] that returns the required data but personalized for the 24
 * Size, according to Andes specifications.
 */
internal class Andes24ThumbnailSize : AndesThumbnailSizeInterface {
    override fun diameter(context: Context): Float = context.resources.getDimension(R.dimen.andes_thumbnail_size_24)
    override fun iconSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_icon_size_16)
    override fun radiusSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_corner_radius_3)
}

/**
 * Implementation of [AndesThumbnailSizeInterface] that returns the required data but personalized for the 32 Size,
 * according to Andes specifications.
 */
internal class Andes32ThumbnailSize : AndesThumbnailSizeInterface {
    override fun diameter(context: Context): Float = context.resources.getDimension(R.dimen.andes_thumbnail_size_32)
    override fun iconSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_icon_size_20)
    override fun radiusSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_corner_radius_3)
}

/**
 * Implementation of [AndesThumbnailSizeInterface] that returns the required data but personalized for the 40 Size,
 * according to Andes specifications.
 */
internal class Andes40ThumbnailSize : AndesThumbnailSizeInterface {
    override fun diameter(context: Context): Float = context.resources.getDimension(R.dimen.andes_thumbnail_size_40)
    override fun iconSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_icon_size_24)
    override fun radiusSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_corner_radius_4)
}

/**
 * Implementation of [AndesThumbnailSizeInterface] that returns the required data but personalized for the 48 Size,
 * according to Andes specifications.
 */
internal class Andes48ThumbnailSize : AndesThumbnailSizeInterface {
    override fun diameter(context: Context): Float = context.resources.getDimension(R.dimen.andes_thumbnail_size_48)
    override fun iconSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_icon_size_24)
    override fun radiusSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_corner_radius_4)
}

/**
 * Implementation of [AndesThumbnailSizeInterface] that returns the required data but personalized for the 56
 * Size, according to Andes specifications.
 */
internal class Andes56ThumbnailSize : AndesThumbnailSizeInterface {
    override fun diameter(context: Context): Float = context.resources.getDimension(R.dimen.andes_thumbnail_size_56)
    override fun iconSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_icon_size_32)
    override fun radiusSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_corner_radius_4)
}

/**
 * Implementation of [AndesThumbnailSizeInterface] that returns the required data but personalized for the 64
 * Size, according to Andes specifications.
 */
internal class Andes64ThumbnailSize : AndesThumbnailSizeInterface {
    override fun diameter(context: Context): Float = context.resources.getDimension(R.dimen.andes_thumbnail_size_64)
    override fun iconSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_icon_size_32)
    override fun radiusSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_corner_radius_4)
}

/**
 * Implementation of [AndesThumbnailSizeInterface] that returns the required data but personalized for the 72
 * Size, according to Andes specifications.
 */
internal class Andes72ThumbnailSize : AndesThumbnailSizeInterface {

    override fun diameter(context: Context): Float = context.resources.getDimension(R.dimen.andes_thumbnail_size_72)
    override fun iconSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_icon_size_32)
    override fun radiusSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_corner_radius_5)
}

/**
 * Implementation of [AndesThumbnailSizeInterface] that returns the required data but personalized for the 80
 * Size, according to Andes specifications.
 */
internal class Andes80ThumbnailSize : AndesThumbnailSizeInterface {

    override fun diameter(context: Context): Float = context.resources.getDimension(R.dimen.andes_thumbnail_size_80)
    override fun iconSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_icon_size_48)
    override fun radiusSize(context: Context): Float =
        context.resources.getDimension(R.dimen.andes_thumbnail_corner_radius_5)
}
