package com.rick.royalbank.utils

import android.icu.text.NumberFormat
import androidx.databinding.BindingAdapter
import android.widget.TextView
import java.math.BigDecimal
import java.util.*

@BindingAdapter("setAmount")
fun setAmount(textView: TextView, amount: BigDecimal) {

    val monetaryFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val currency = monetaryFormat.format(amount)

    textView.text = currency
}