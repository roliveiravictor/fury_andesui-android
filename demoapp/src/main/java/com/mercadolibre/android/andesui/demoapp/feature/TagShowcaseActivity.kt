package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.constraint.Group
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.demoapp.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.launchSpecs
import com.mercadolibre.android.andesui.tag.AndesTagSimple
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContentDot
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContentIcon
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContentImage
import com.mercadolibre.android.andesui.tag.size.AndesTagSize
import com.mercadolibre.android.andesui.tag.type.AndesTagType
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState
import com.mercadolibre.android.andesui.utils.validateColor

class TagShowcaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_tag)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
    }

    class AndesShowcasePagerAdapter(private val context: Context) : PagerAdapter() {

        var views: List<View>

        init {
            views = initViews()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            container.addView(views[position])
            return views[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
            container.removeView(view as View?)
        }

        override fun isViewFromObject(view: View, other: Any): Boolean {
            return view == other
        }

        override fun getCount(): Int = views.size

        private fun initViews(): List<View> {
            val inflater = LayoutInflater.from(context)

            val dinamicMessagesLayout = addDinamicTAG(inflater)
            val staticMessagesLayout = addStaticTAG(inflater)

            return listOf(dinamicMessagesLayout, staticMessagesLayout)
        }

        private fun addStaticTAG(inflater: LayoutInflater): View {
            val layoutTag = inflater.inflate(R.layout.andesui_tags_showcase, null, false) as ScrollView

            layoutTag.findViewById<AndesButton>(R.id.andesui_demoapp_andes_tag_specs_button).setOnClickListener {
                launchSpecs(it.context, AndesSpecs.TAG)
            }

            val firstColumn = layoutTag.findViewById<LinearLayout>(R.id.firstColumn)
            val secondColumn = layoutTag.findViewById<LinearLayout>(R.id.secondColumn)

            val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, context.resources.getDimension(R.dimen.badge_margin_vertical).toInt())

            val tagSimpleSmall = AndesTagSimple(
                    context,
                    AndesTagType.HIGHLIGHT,
                    AndesTagSize.SMALL,
                    "Lorem Ipsum"
            )
            firstColumn.addView(tagSimpleSmall, params)

            val tagSimpleSmallDismissable = AndesTagSimple(
                    context,
                    AndesTagType.NEUTRAL,
                    AndesTagSize.SMALL,
                    "Dismissable"
            )
            tagSimpleSmallDismissable.isDismissable = true

            firstColumn.addView(tagSimpleSmallDismissable, params)

            val tagSimpleSmallDismissableWithCallback = AndesTagSimple(
                    context,
                    AndesTagType.SUCCESS,
                    AndesTagSize.SMALL,
                    "Callback"
            )
            tagSimpleSmallDismissableWithCallback.isDismissable = true
            tagSimpleSmallDismissableWithCallback.setupDismsissableCallback(View.OnClickListener {
                Toast.makeText(context, "Dismiss onClicked", Toast.LENGTH_LONG).show()
            })
            firstColumn.addView(tagSimpleSmallDismissableWithCallback, params)

            val tagSimple = AndesTagSimple(
                    context,
                    AndesTagType.ERROR,
                    AndesTagSize.LARGE,
                    "Lorem Ipsum"
            )
            firstColumn.addView(tagSimple, params)

            val tagSimpleDismissable = AndesTagSimple(
                    context,
                    AndesTagType.NEUTRAL,
                    AndesTagSize.LARGE,
                    "Dismissable"
            )
            tagSimpleDismissable.isDismissable = true
            firstColumn.addView(tagSimpleDismissable, params)

            val tagSimpleDismissableWithCallback = AndesTagSimple(
                    context,
                    AndesTagType.WARNING,
                    AndesTagSize.LARGE,
                    "Callback"
            )
            tagSimpleDismissableWithCallback.isDismissable = true
            tagSimpleDismissableWithCallback.setupDismsissableCallback(View.OnClickListener {
                Toast.makeText(context, "Dismiss onClicked", Toast.LENGTH_LONG).show()
            })
            firstColumn.addView(tagSimpleDismissableWithCallback, params)

            // Left content DOT
            val tagSimpleDot = AndesTagSimple(
                    context,
                    AndesTagType.NEUTRAL,
                    AndesTagSize.LARGE,
                    "Amarillo"
            )
            tagSimpleDot.leftContent = LeftContent(
                    dot = LeftContentDot("#FFEC2B")
            )
            firstColumn.addView(tagSimpleDot, params)

            val tagSimpleDotDismissable = AndesTagSimple(
                    context,
                    AndesTagType.NEUTRAL,
                    AndesTagSize.LARGE,
                    "Azul"
            )
            tagSimpleDotDismissable.isDismissable = true
            tagSimpleDotDismissable.leftContent = LeftContent(
                    dot = LeftContentDot("#2B5BFF")
            )
            firstColumn.addView(tagSimpleDotDismissable, params)

            val tagSimpleDotText = AndesTagSimple(
                    context,
                    AndesTagType.NEUTRAL,
                    AndesTagSize.LARGE,
                    "Camila Farías"
            )
            tagSimpleDotText.leftContent = LeftContent(
                    dot = LeftContentDot("#2E97FF", "CF", "#FFFFFF")
            )
            secondColumn.addView(tagSimpleDotText, params)

            val tagSimpleDotTextDismissable = AndesTagSimple(
                    context,
                    AndesTagType.NEUTRAL,
                    AndesTagSize.LARGE,
                    "Camila Farías"
            )
            tagSimpleDotTextDismissable.isDismissable = true
            tagSimpleDotTextDismissable.leftContent = LeftContent(
                    dot = LeftContentDot("#E3E3E3", "CF", "#8C8C8C")
            )
            secondColumn.addView(tagSimpleDotTextDismissable, params)

            val tagSimpleIcon = AndesTagSimple(
                    context,
                    AndesTagType.NEUTRAL,
                    AndesTagSize.LARGE,
                    "Tag con icono"
            )
            tagSimpleIcon.leftContent = LeftContent(
                    icon = LeftContentIcon(
                            backgroundColor = "#10B906",
                            path = "andes_ui_feedback_success_24",
                            iconColor = "#FFFFFF"
                    )
            )
            secondColumn.addView(tagSimpleIcon, params)

            val tagSimpleIconDismissable = AndesTagSimple(
                    context,
                    AndesTagType.NEUTRAL,
                    AndesTagSize.LARGE,
                    "Icono"
            )
            tagSimpleIconDismissable.isDismissable = true
            val drawable = context.resources.getDrawable(R.drawable.andes_navegacion_ajustes)
            tagSimpleIconDismissable.leftContent = LeftContent(
                    icon = LeftContentIcon(
                            backgroundColor = "#E7E7E7",
                            icon = drawable,
                            iconColor = "#8C8C8C"
                    )
            )
            secondColumn.addView(tagSimpleIconDismissable, params)

            Glide.with(context)
                    .load("https://imagenes.universia.net/gc/net/images/gente/f/fr/fra/frases_de_confianza.jpg")
                    .asBitmap()
                    .into(object : SimpleTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap?>?) {
                            if (resource != null) {
                                val tagSimpleImage = AndesTagSimple(
                                        context,
                                        AndesTagType.NEUTRAL,
                                        AndesTagSize.LARGE,
                                        "Tatiana"
                                )
                                tagSimpleImage.leftContent = LeftContent(image = LeftContentImage(resource))
                                secondColumn.addView(tagSimpleImage, params)
                            }
                        }
                    })

            Glide.with(context)
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:" +
                            "ANd9GcQPUjE4Vno7piEReTnCrwR3oSxBxkfhIYAnXzcs7zC0ekNPPwnc&s")
                    .asBitmap()
                    .into(object : SimpleTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap?>?) {
                            if (resource != null) {
                                val tagSimpleImage = AndesTagSimple(
                                        context,
                                        AndesTagType.NEUTRAL,
                                        AndesTagSize.LARGE,
                                        "Lorenzo"
                                )
                                tagSimpleImage.isDismissable = true
                                tagSimpleImage.leftContent = LeftContent(image = LeftContentImage(resource))
                                secondColumn.addView(tagSimpleImage, params)
                            }
                        }
                    })

            return layoutTag
        }

        private fun addDinamicTAG(inflater: LayoutInflater): View {
            val layoutTag = inflater.inflate(
                    R.layout.andesui_tag_showcase_change, null, false
            ) as ScrollView

            val andesTagSimple: AndesTagSimple = layoutTag.findViewById(R.id.andesui_tag)

            val typeSpinner: Spinner = layoutTag.findViewById(R.id.type_spinner)
            ArrayAdapter.createFromResource(
                    context, R.array.type_spinner, android.R.layout.simple_spinner_item)
                    .also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        typeSpinner.adapter = adapter
                    }

            val groupDot: Group = layoutTag.findViewById(R.id.group_dot)
            val groupIcon: Group = layoutTag.findViewById(R.id.group_icon)
            val groupImage: Group = layoutTag.findViewById(R.id.group_image)
            groupDot.visibility = View.GONE
            groupIcon.visibility = View.GONE
            groupImage.visibility = View.GONE

            val sizeSpinner: Spinner = layoutTag.findViewById(R.id.size_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.size_spinner,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sizeSpinner.adapter = adapter
            }

            val leftContentSpinner: Spinner = layoutTag.findViewById(R.id.left_content_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.left_content_spinner,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                leftContentSpinner.adapter = adapter
            }

            val backgroundColor: AndesTextfield = layoutTag.findViewById(R.id.left_content_background_color)
            backgroundColor.setPrefix("#")
            backgroundColor.placeholder = "FFFFFF"

            val color: AndesTextfield = layoutTag.findViewById(R.id.left_content_text_color)
            color.setPrefix("#")
            color.placeholder = "FFFFFF"

            val iconBackgroundColor: AndesTextfield = layoutTag.findViewById(R.id.left_content_icon_background_color)
            iconBackgroundColor.setPrefix("#")
            iconBackgroundColor.placeholder = "FFFFFF"

            val iconColor: AndesTextfield = layoutTag.findViewById(R.id.left_content_icon_color)
            iconColor.setPrefix("#")
            iconColor.placeholder = "FFFFFF"

            val array = arrayOf("Warning", "Success", "Close", "Info")
            val iconsSpinner: Spinner = layoutTag.findViewById(R.id.icon_spinner)
            val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                    context, android.R.layout.simple_spinner_item, array
            )
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            iconsSpinner.adapter = spinnerArrayAdapter

            val arrayUrl = arrayOf("Image 1", "Image 2", "Image 3")
            val urlSpinner: Spinner = layoutTag.findViewById(R.id.spinner_url)
            val urlArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                    context, android.R.layout.simple_spinner_item, arrayUrl
            )
            urlArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            urlSpinner.adapter = urlArrayAdapter

            val labelText: AndesTextfield = layoutTag.findViewById(R.id.label_text)
            labelText.placeholder = context.resources.getString(R.string.andes_textfield_label_text)

            val dismissable: Switch = layoutTag.findViewById(R.id.dismissable)

            leftContentSpinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
                ) {
                    when (leftContentSpinner.getItemAtPosition(position)) {
                        "Dot" -> {
                            groupDot.visibility = View.VISIBLE
                            groupIcon.visibility = View.GONE
                            groupImage.visibility = View.GONE
                        }
                        "Icon" -> {
                            groupDot.visibility = View.GONE
                            groupIcon.visibility = View.VISIBLE
                            groupImage.visibility = View.GONE
                        }
                        "Image" -> {
                            groupDot.visibility = View.GONE
                            groupIcon.visibility = View.GONE
                            groupImage.visibility = View.VISIBLE
                        }
                        else -> {
                            groupDot.visibility = View.GONE
                            groupIcon.visibility = View.GONE
                            groupImage.visibility = View.GONE
                        }
                    }
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {}
            }

            val clearButton: AndesButton = layoutTag.findViewById(R.id.clear_button)
            val changeButton: AndesButton = layoutTag.findViewById(R.id.change_button)
            val textDot: AndesTextfield = layoutTag.findViewById(R.id.left_content_text)

            clearButton.setOnClickListener {
                andesTagSimple.visibility = View.VISIBLE

                dismissable.isChecked = false
                typeSpinner.setSelection(0)
                sizeSpinner.setSelection(0)
                leftContentSpinner.setSelection(0)
                labelText.text = ""
                labelText.state = AndesTextfieldState.IDLE
                labelText.helper = null

                andesTagSimple.text = "Simple tag"
                andesTagSimple.type = AndesTagType.NEUTRAL
                andesTagSimple.size = AndesTagSize.LARGE
                andesTagSimple.isDismissable = false
                andesTagSimple.leftContent = null
            }

            changeButton.setOnClickListener {

                andesTagSimple.visibility = View.VISIBLE

                val isDismissable = dismissable.isChecked
                val type = AndesTagType.fromString(typeSpinner.selectedItem as String)
                val size = AndesTagSize.fromString(sizeSpinner.selectedItem as String)

                if (labelText.text.isNullOrEmpty()) {
                    labelText.state = AndesTextfieldState.ERROR
                    labelText.helper = "Este campo es requerido"
                    return@setOnClickListener
                } else {
                    labelText.state = AndesTextfieldState.IDLE
                    labelText.helper = null
                }
                val text = labelText.text

                var leftContent: LeftContent? = null
                when (leftContentSpinner.selectedItem) {
                    "Dot" -> {
                        if (backgroundColor.text.isNullOrEmpty()) {
                            backgroundColor.state = AndesTextfieldState.ERROR
                            backgroundColor.helper = "Este campo es requerido"
                            return@setOnClickListener
                        } else if (!validateColor("#${backgroundColor.text!!}")) {
                            backgroundColor.state = AndesTextfieldState.ERROR
                            backgroundColor.helper = "Color inválido"
                            return@setOnClickListener
                        } else {
                            backgroundColor.state = AndesTextfieldState.IDLE
                            backgroundColor.helper = ""
                        }
                        var textColor: String? = null
                        if (!color.text.isNullOrEmpty()) {
                            if (!validateColor("#${color.text!!}")) {
                                color.state = AndesTextfieldState.ERROR
                                color.helper = "Color inválido"
                                return@setOnClickListener
                            } else {
                                color.state = AndesTextfieldState.IDLE
                                color.helper = ""
                                textColor = "#${color.text}"
                            }
                        } else {
                            color.state = AndesTextfieldState.IDLE
                            color.helper = ""
                        }
                        leftContent = LeftContent(dot = LeftContentDot(
                                "#${backgroundColor.text!!}",
                                textDot.text, textColor)
                        )
                    }
                    "Icon" -> {
                        if (iconBackgroundColor.text.isNullOrEmpty()) {
                            iconBackgroundColor.state = AndesTextfieldState.ERROR
                            iconBackgroundColor.helper = "Este capo es requerido"
                            return@setOnClickListener
                        } else if (!validateColor("#${iconBackgroundColor.text!!}")) {
                            iconBackgroundColor.state = AndesTextfieldState.ERROR
                            iconBackgroundColor.helper = "Color inválido"
                            return@setOnClickListener
                        } else {
                            iconBackgroundColor.state = AndesTextfieldState.IDLE
                            iconBackgroundColor.helper = ""
                        }
                        var icon: String? = null
                        if (!iconColor.text.isNullOrEmpty()) {
                            if (!validateColor("#${iconColor.text!!}")) {
                                iconColor.state = AndesTextfieldState.ERROR
                                iconColor.helper = "Color inválido"
                                return@setOnClickListener
                            } else {
                                iconColor.state = AndesTextfieldState.IDLE
                                iconColor.helper = ""
                                icon = "#${iconColor.text}"
                            }
                        } else {
                            iconColor.state = AndesTextfieldState.IDLE
                            iconColor.helper = ""
                        }
                        val path = when (iconsSpinner.selectedItem) {
                            "Warning" -> "andes_ui_feedback_warning_24"
                            "Success" -> "andes_ui_feedback_success_24"
                            "Close" -> "andes_ui_close_24"
                            "Info" -> "andes_ui_feedback_info_24"
                            else -> "andes_ui_close_24"
                        }
                        leftContent = LeftContent(icon = LeftContentIcon(
                                backgroundColor = "#${iconBackgroundColor.text!!}",
                                path = path,
                                iconColor = icon)
                        )
                    }
                    "Image" -> {
                        val pictureUrl = when (urlSpinner.selectedItemPosition) {
                            0 -> "https://conceptodefinicion.de/wp-content/uploads/2014/10/persona.jpg"
                            1 -> "https://eststatic.com/2015/responsive-images/" +
                                 "virtudes-de-una-persona___large_990_660.jpg"
                            else -> "https://imagenes.universia.net/gc/net/images/" +
                                    "gente/f/fr/fra/frases_de_confianza.jpg"
                        }
                        Glide.with(context)
                                .load(pictureUrl)
                                .asBitmap()
                                .into(object : SimpleTarget<Bitmap>() {
                                    override fun onResourceReady(
                                        resource: Bitmap?,
                                        glideAnimation: GlideAnimation<in Bitmap?>?
                                    ) {
                                        if (resource != null) {
                                            leftContent = LeftContent(image = LeftContentImage(resource))
                                            andesTagSimple.leftContent = leftContent
                                        }
                                    }
                                })
                    }
                }

                andesTagSimple.text = text
                andesTagSimple.type = type
                andesTagSimple.size = size
                andesTagSimple.isDismissable = isDismissable
                andesTagSimple.leftContent = leftContent
            }

            return layoutTag
        }
    }
}
