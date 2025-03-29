package com.example.slideshowapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Quote(
    @StringRes val captionResourceId : Int,
    @StringRes val personResourceId : Int,
    @DrawableRes val imageResourceId : Int
)
