package com.mercadolibre.android.andesui.demoapp.feature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.mercadolibre.android.andesui.demoapp.R
import io.noties.markwon.Markwon
import java.io.ByteArrayOutputStream
import java.io.InputStream

class ProgressIndicatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_demoapp_whatsnew)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_whatsnew)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
