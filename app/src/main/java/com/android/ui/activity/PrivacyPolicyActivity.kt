package com.android.ui.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.vaibhavi.android.R
import kotlinx.android.synthetic.main.main_toolbar.*
import kotlinx.android.synthetic.main.privacy_policy_activity.*

class PrivacyPolicyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.privacy_policy_activity)
        imageviewbacktoolbar.setOnClickListener {
            finish()
        }

        textViewTitle.text = resources.getString(R.string.privacy_policy)
        webviewPrivacyPloicy.loadUrl("https://demo.adsandurl.com/money-making/privacy-policy.php")

    }
}