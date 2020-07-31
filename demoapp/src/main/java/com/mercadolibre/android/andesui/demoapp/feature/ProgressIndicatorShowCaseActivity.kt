package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ScrollView
import android.widget.Spinner
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.checkbox.AndesCheckbox
import com.mercadolibre.android.andesui.checkbox.align.AndesCheckboxAlign
import com.mercadolibre.android.andesui.checkbox.status.AndesCheckboxStatus
import com.mercadolibre.android.andesui.checkbox.type.AndesCheckboxType
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState

class ProgressIndicatorShowCaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_progress_indicator)
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

            val dynamicCheckboxLayout = addDynamicProgressIndicator(inflater)

            return listOf(dynamicCheckboxLayout)
        }

        private fun addDynamicProgressIndicator(inflater: LayoutInflater): View {
            val layoutCheckbox = inflater.inflate(R.layout.andesui_dynamic_progress_indicator_showcase, null, false) as ScrollView

            val checkbox: AndesCheckbox = layoutCheckbox.findViewById(R.id.andesCheckbox)

            val spinnerType: Spinner = layoutCheckbox.findViewById(R.id.spinnerType)
            ArrayAdapter.createFromResource(
                    context, R.array.type_checkbox_spinner, android.R.layout.simple_spinner_item)
                    .also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerType.adapter = adapter
                    }

            val spinnerAlign: Spinner = layoutCheckbox.findViewById(R.id.spinnerType)
            ArrayAdapter.createFromResource(
                    context, R.array.align_checkbox_spinner, android.R.layout.simple_spinner_item)
                    .also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerAlign.adapter = adapter
                    }

            val clearButton: AndesButton = layoutCheckbox.findViewById(R.id.buttonClear)
            val changeButton: AndesButton = layoutCheckbox.findViewById(R.id.buttonUpdate)
            val andesTextfield: AndesTextfield = layoutCheckbox.findViewById(R.id.andesTextfield)

            clearButton.setOnClickListener {
                spinnerType.setSelection(0)
                spinnerAlign.setSelection(0)

                andesTextfield.text = ""
                andesTextfield.state = AndesTextfieldState.IDLE
                andesTextfield.helper = null

                checkbox.align = AndesCheckboxAlign.LEFT
                checkbox.type = AndesCheckboxType.IDLE
                checkbox.status = AndesCheckboxStatus.UNSELECTED
                checkbox.text = context.resources.getString(R.string.andes_checkbox_text)
            }

            changeButton.setOnClickListener {
                if (andesTextfield.text.isNullOrEmpty()) {
                    andesTextfield.state = AndesTextfieldState.ERROR
                    andesTextfield.helper = "Este campo es requerido"
                    return@setOnClickListener
                } else {
                    andesTextfield.state = AndesTextfieldState.IDLE
                    andesTextfield.helper = null
                }

                val type = when (spinnerType.selectedItem) {
                    "Idle" -> AndesCheckboxType.IDLE
                    "Error" -> AndesCheckboxType.ERROR
                    "Disabled" -> AndesCheckboxType.DISABLED
                    else -> AndesCheckboxType.IDLE
                }

                checkbox.type = type
                checkbox.text = andesTextfield.text
            }

            return layoutCheckbox
        }
    }
}
