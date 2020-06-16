package com.mercadolibre.android.andesui.progress

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.progress.extensions.get
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorAttrs
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorAttrsParser
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorConfiguration
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorConfigurationFactory
import com.mercadolibre.android.andesui.progress.factory.AndesProgressIndicatorDeterminateConfiguration
import com.mercadolibre.android.andesui.progress.size.AndesProgressIndicatorSize

class AndesProgressIndicator : ConstraintLayout {

    private lateinit var andesProgressIndicatorAttrs: AndesProgressIndicatorAttrs

    /**
     * Constructor for creating an AndesButton via XML.
     * The [attrs] are the attributes specified in the parameters of XML.
     *
     * Hope you are using the parameters specified in attrs.xml file: andesButtonHierarchy, andesButtonSize, andesButtonLeftIconCustom, etc.
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    /**
     * Constructor for creating an AndesButton via XML.
     * The [attrs] are the attributes specified in the parameters of XML.
     * The [defStyleAttr] is not considered because we take care of all Andes styling for you.
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs)

    fun create() {
        val progress = AndesProgressIndicatorConfigurationFactory.create(context,
                AndesProgressIndicatorAttrs(
                        AndesProgressIndicatorSize.SMALL,
                        AndesColor(R.color.andes_accent_color),
                        AndesColor(R.color.andes_accent_color),
                        "label",
                        false,
                        0,
                        R.drawable.andes_border
                )
        ).get<AndesProgressIndicatorDeterminateConfiguration>()
    }

    /**
     * Sets the proper configuration for this button based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesProgressIndicatorAttrs = AndesProgressIndicatorAttrsParser.parse(context, attrs)
        setupComponents(createConfig())
    }

    private fun setupComponents(config: AndesProgressIndicatorConfiguration) {

    }

    private fun createConfig() = AndesProgressIndicatorConfigurationFactory.create(context, andesProgressIndicatorAttrs)
}