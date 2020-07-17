package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.demoapp.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.launchSpecs
import com.mercadolibre.android.andesui.message.AndesMessage
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.type.AndesMessageType

class MessageShowcaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_message)
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

            val staticMessagesLayout = addStaticMessages(inflater)
            val dynamicMessageLayout = addDynamicMessage(inflater)

            return listOf(dynamicMessageLayout, staticMessagesLayout)
        }

        private fun addDynamicMessage(inflater: LayoutInflater): View {
            val layoutMessagesChange = inflater.inflate(
                    R.layout.andesui_message_showcase_change, null, false
            ) as ScrollView

            val hierarchySpinner: Spinner = layoutMessagesChange.findViewById(R.id.hierarchy_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.hierarchy_spinner,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                hierarchySpinner.adapter = adapter
            }

            val typeSpinner: Spinner = layoutMessagesChange.findViewById(R.id.type_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.state_spinner,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                typeSpinner.adapter = adapter
            }

            val dismissableCheckbox = layoutMessagesChange.findViewById<CheckBox>(R.id.dismissable_checkbox)

            val bodyText = layoutMessagesChange.findViewById<EditText>(R.id.body_text)

            val titleText = layoutMessagesChange.findViewById<EditText>(R.id.title_text)

            val primaryActionText = layoutMessagesChange.findViewById<EditText>(R.id.primary_action_text)

            val secondaryActionText = layoutMessagesChange.findViewById<EditText>(R.id.secondary_action_text)

            val linkActionText = layoutMessagesChange.findViewById<EditText>(R.id.link_action_text)

            val changeButton = layoutMessagesChange.findViewById<AndesButton>(R.id.change_button)
            val changeMessage = layoutMessagesChange.findViewById<AndesMessage>(R.id.message)

            changeButton.setOnClickListener {
                changeMessage.isDismissable = dismissableCheckbox.isChecked
                changeMessage.title = titleText.text.toString()
                if (bodyText.text.toString().isEmpty()) {
                    Toast.makeText(
                            context,
                            "Message cannot be visualized with null body",
                            Toast.LENGTH_SHORT
                    ).show()
                } else {
                    changeMessage.body = bodyText.text.toString()
                }

                changeMessage.type = AndesMessageType.fromString(typeSpinner.selectedItem.toString())

                changeMessage.hierarchy = AndesMessageHierarchy.fromString(hierarchySpinner.selectedItem.toString())

                if (primaryActionText.text.toString().isNotEmpty()) {
                    changeMessage.setupPrimaryAction(primaryActionText.text.toString(), View.OnClickListener {
                        Toast.makeText(context, "Primary onClick", Toast.LENGTH_SHORT).show()
                    })
                    changeMessage.hideLinkAction()
                } else {
                    changeMessage.hidePrimaryAction()
                }

                if (dismissableCheckbox.isChecked) {
                    changeMessage.setupDismissableCallback(View.OnClickListener {
                        Toast.makeText(context, "Dismiss onClick", Toast.LENGTH_LONG).show()
                    })
                }

                if (secondaryActionText.text.toString().isNotEmpty()) {
                    when {
                        primaryActionText.text.toString() != "" -> {
                            changeMessage.setupSecondaryAction(
                                    secondaryActionText.text.toString(), View.OnClickListener {
                                        Toast.makeText(context, "Secondary onClick", Toast.LENGTH_SHORT).show()
                                    }
                            )
                        }
                        else -> {
                            Toast.makeText(
                                    context,
                                    "Cannot set a secondary action without a primary one",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    changeMessage.hideSecondaryAction()
                }

                if (linkActionText.text.toString().isNotEmpty()) {
                    when {
                        primaryActionText.text.toString() == "" -> {
                            changeMessage.setupLinkAction(linkActionText.text.toString(), View.OnClickListener {
                                Toast.makeText(context, "link onClick", Toast.LENGTH_SHORT).show()
                            }, changeMessage.hierarchy)
                        }
                        else -> {
                            Toast.makeText(
                                    context,
                                    "Cannot set a link action with a primary one",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    changeMessage.hideLinkAction()
                }

                changeMessage.visibility = View.VISIBLE
            }

            return layoutMessagesChange
        }

        private fun addStaticMessages(inflater: LayoutInflater): View {
            val layoutMessages = inflater.inflate(
                    R.layout.andesui_message_showcase, null, false
            ) as ScrollView

            val button = layoutMessages.findViewById<AndesButton>(R.id.button)

            layoutMessages.findViewById<AndesButton>(R.id.andesui_demoapp_andes_specs_message).setOnClickListener {
                launchSpecs(it.context, AndesSpecs.MESSAGE)
            }

            button.setOnClickListener {
                val message = layoutMessages.findViewById<AndesMessage>(R.id.message_loud)
                val hour = System.currentTimeMillis().toString()
                message.title = ("The current millis are: $hour")
                message.hierarchy = (AndesMessageHierarchy.LOUD)
                message.type = (AndesMessageType.SUCCESS)
                message.isDismissable = false
                message.body = "I insist. Current millis are: $hour"
            }

            return layoutMessages
        }
    }
}
