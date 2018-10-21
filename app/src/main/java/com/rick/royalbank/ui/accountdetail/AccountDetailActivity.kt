package com.rick.royalbank.ui.accountdetail

import android.graphics.Color
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rick.royalbank.R
import com.rick.royalbank.utils.ROI
import kotlin.math.sign

class AccountDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_detail)

        setupLayout()
    }

    private fun setupLayout() {

        val roi = intent.extras?.getDouble(ROI)
        val roiTextView = findViewById<TextView>(R.id.roi)

        roi?.let {
            roiTextView.text = getString(R.string.roi_label, it)
            changeBackground(it)
        }
    }

    /**
     * Passing "roi" as parameter is easier to test
     */
    private fun changeBackground(roi: Double){

        val rootLayout = findViewById<RelativeLayout>(R.id.rootLayout)

        when (roi.sign) {
            -1.0 -> rootLayout.setBackgroundColor(Color.RED)
            1.0 -> rootLayout.setBackgroundColor(Color.GREEN)
        }
    }
}