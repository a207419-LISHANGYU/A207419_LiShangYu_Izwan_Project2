package com.example.a207419_lishangyu_izwan_project1.api

data class AdviceResponse(
    val slip: AdviceSlip
)

data class AdviceSlip(
    val id: Int,
    val advice: String
)