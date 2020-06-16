package com.mercadolibre.android.andesui.demoapp

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.andesui_demoapp_main.*

/**
 * Main activity class
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_demoapp_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_app_name)

        setupTags()
        setupBadges()
        setupButtons()
        setupMessages()
        setupTextfield()
        setupWhatsNew()
        setupContributionTrigger()
        setupAndesSpecsWeb()
        setupProgressIndicator()
    }

    private fun setupProgressIndicator() {
        andesui_progress_indicator.setOnClickListener {
            launchIntent("meli://andes/progress-indicator")
        }
    }

    private fun setupTags() {
        andesui_tags.setOnClickListener {
            launchIntent("meli://andes/tag")
        }
    }

    private fun setupBadges() {
        andesui_badges.setOnClickListener {
            launchIntent("meli://andes/badge")
        }
    }

    private fun setupButtons() {
        andesui_buttons.setOnClickListener {
            launchIntent("meli://andes/button")
        }
    }

    private fun setupMessages() {
        andesui_messages.setOnClickListener {
            launchIntent("meli://andes/message")
        }
    }

    private fun setupTextfield() {
        andesui_textfield.setOnClickListener {
            launchIntent("meli://andes/textfield")
        }
    }

    private fun setupWhatsNew() {
        andesui_demoapp_changelog.setupPrimaryAction(
            getString(R.string.andesui_demoapp_whatsnew_main_action),
            View.OnClickListener { launchIntent("meli://andes/whats-new") }
        )
    }

    private fun setupAndesSpecsWeb() {
        andesui_demoapp_andes_specs.setOnClickListener {
            launchSpecs(this, AndesSpecs.HOME_PAGE)
        }
    }

    private fun setupContributionTrigger() {
        andesui_demoapp_contribution.setOnClickListener {
            launchIntent("https://meli.workplace.com/notes/andes-ui/c%C3%B3mo-contribuir-en-andes-ui/2559399620854933")
        }
    }

    private fun launchIntent(uri: String) {
        val launchIntent = Intent(ACTION_VIEW, Uri.parse(uri))
        startActivity(launchIntent)
    }
}
