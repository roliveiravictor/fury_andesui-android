package com.mercadolibre.android.andesui.thumbnail

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.thumbnail.factory.AndesThumbnailAttrs
import com.mercadolibre.android.andesui.thumbnail.factory.AndesThumbnailAttrsParser
import com.mercadolibre.android.andesui.thumbnail.factory.AndesThumbnailConfiguration
import com.mercadolibre.android.andesui.thumbnail.factory.AndesThumbnailConfigurationFactory
import com.mercadolibre.android.andesui.thumbnail.hierarchy.AndesThumbnailHierarchy
import com.mercadolibre.android.andesui.thumbnail.size.AndesThumbnailSize
import com.mercadolibre.android.andesui.thumbnail.state.AndesThumbnailState
import com.mercadolibre.android.andesui.thumbnail.type.AndesThumbnailType

class AndesThumbnail : FrameLayout {

    /**
     * Getter and setter for [accentColor].
     */
    var accentColor: AndesColor
        get() = andesThumbnailAttrs.andesThumbnailAccentColor
        set(value) {
            andesThumbnailAttrs = andesThumbnailAttrs.copy(andesThumbnailAccentColor = value)
            val config = createConfig()
            setupBackground(config)
            setupImageColor(config.iconColor)
        }

    /**
     * Getter and setter for [hierarchy].
     */
    var hierarchy: AndesThumbnailHierarchy
        get() = andesThumbnailAttrs.andesThumbnailHierarchy
        set(value) {
            andesThumbnailAttrs = andesThumbnailAttrs.copy(andesThumbnailHierarchy = value)
            val config = createConfig()
            setupBackground(config)
            setupImageColor(config.iconColor)
        }

    /**
     * Getter and setter for [type].
     */
    var type: AndesThumbnailType
        get() = andesThumbnailAttrs.andesThumbnailType
        set(value) {
            andesThumbnailAttrs = andesThumbnailAttrs.copy(andesThumbnailType = value)
            val config = createConfig()
            setupBackground(config)
            setupImage(config)
        }

    /**
     * Getter and setter for [size].
     */
    var size: AndesThumbnailSize
        get() = andesThumbnailAttrs.andesThumbnailSize
        set(value) {
            andesThumbnailAttrs = andesThumbnailAttrs.copy(andesThumbnailSize = value)
            val config = createConfig()
            setupBackgroundSize(config.size)
            setupImageSize(config.iconSize)
        }

    /**
     * Getter and setter for [state].
     */
    var state: AndesThumbnailState
        get() = andesThumbnailAttrs.andesThumbnailState
        set(value) {
            andesThumbnailAttrs = andesThumbnailAttrs.copy(andesThumbnailState = value)
            val config = createConfig()
            setupBackground(config)
            setupImageColor(config.iconColor)
        }

    private val imageFrame by lazy { findViewById<ImageView>(R.id.andes_thumbnail_image) }

    private lateinit var andesThumbnailAttrs: AndesThumbnailAttrs

    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException("Constructor without parameters in Andes Thumbnail is not allowed." +
            " You must provide some attributes.")
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
        context: Context,
        accentColor: AndesColor,
        hierarchy: AndesThumbnailHierarchy = AndesThumbnailHierarchy.LOUD,
        image: Drawable,
        type: AndesThumbnailType = AndesThumbnailType.ICON,
        size: AndesThumbnailSize = AndesThumbnailSize.SIZE_48,
        state: AndesThumbnailState = AndesThumbnailState.ENABLED
    ) : super(context) {
        initAttrs(accentColor, hierarchy, image, type, size, state)
    }

    /**
     * Sets the proper [config] for this thumbnail based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesThumbnailAttrs = AndesThumbnailAttrsParser.parse(context, attrs)
        val config = AndesThumbnailConfigurationFactory.create(context, andesThumbnailAttrs)
        setupComponents(config)
    }

    private fun initAttrs(
        accentColor: AndesColor,
        hierarchy: AndesThumbnailHierarchy,
        image: Drawable,
        type: AndesThumbnailType,
        size: AndesThumbnailSize,
        state: AndesThumbnailState
    ) {
        andesThumbnailAttrs = AndesThumbnailAttrs(accentColor, hierarchy, image, type, size, state)
        val config = AndesThumbnailConfigurationFactory.create(context, andesThumbnailAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this thumbnail.
     * Is like a choreographer ;)
     */
    private fun setupComponents(config: AndesThumbnailConfiguration) {
        initComponents()
        setupViewId()

        setupBackground(config)
        setupImage(config)
    }

    /**
     * Creates all the views that are part of this thumbnail.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        LayoutInflater.from(context).inflate(R.layout.andes_layout_thumbnail, this)
    }

    /**
     * Sets a view id to this thumbnail.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    private fun setupBackground(config: AndesThumbnailConfiguration) {
        val shape = GradientDrawable()
        if (config.hasBorder) {
            val borderSize = resources.getDimension(R.dimen.andes_thumbnail_icon_border)
            shape.setStroke(borderSize.toInt(), config.borderColor.colorIntToAlpha(context))
        } else {
            shape.setColor(config.backgroundColor.colorIntToAlpha(context))
        }

        background = shape

        setupBackgroundSize(config.size)
    }

    private fun setupBackgroundSize(size: Float) {
        if (background != null) {
            with(background as GradientDrawable) {
                cornerRadius = size
                setSize(size.toInt(), size.toInt())
            }
        }
    }

    private fun setupImage(config: AndesThumbnailConfiguration) {
        val unwrappedDrawable = config.image
        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable)

        imageFrame.background = wrappedDrawable
        setupImageColor(config.iconColor)
        setupImageSize(config.iconSize)
    }

    private fun setupImageColor(iconColor: AndesColor) {
        DrawableCompat.setTint(imageFrame.background, iconColor.colorInt(context))
    }

    private fun setupImageSize(iconSize: Int) {
        imageFrame.layoutParams = LayoutParams(iconSize, iconSize, Gravity.CENTER)
    }

    private fun createConfig() = AndesThumbnailConfigurationFactory.create(context, andesThumbnailAttrs)
}
