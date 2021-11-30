package com.skycompose.skysportsapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.skycompose.skysportsapplication.R

private val SkyFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.sky_text_reg,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.sky_text_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        )
    )
)

val SkyTypography = Typography(
    h1 = TextStyle(fontFamily = SkyFontFamily, fontSize = 48.sp),
    h2 = TextStyle(fontFamily = SkyFontFamily, fontSize = 44.sp),
    h3 = TextStyle(fontFamily = SkyFontFamily, fontSize = 40.sp),
    h4 = TextStyle(fontFamily = SkyFontFamily, fontSize = 36.sp),
    h5 = TextStyle(fontFamily = SkyFontFamily, fontSize = 18.sp, fontWeight = FontWeight.Bold),
    h6 = TextStyle(fontFamily = SkyFontFamily, fontSize = 18.sp)
)