package com.example.a30diferentmitsubishi.Data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Car(
    val number: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
) {
}