package com.rick.royalbank.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Account (
        val id: Int,
        val name: String,
        val amount: BigDecimal,

        @SerializedName("ROI")
        val roi: Double
)