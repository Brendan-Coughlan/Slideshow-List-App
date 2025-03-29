package com.example.slideshowapp.data

import com.example.slideshowapp.R
import com.example.slideshowapp.model.Quote

class Datasource {
    fun loadQuotes(): List<Quote> {
        return listOf<Quote>(
            Quote(R.string.caption1, R.string.person1, R.drawable.image_1),
            Quote(R.string.caption2, R.string.person2, R.drawable.image_2),
            Quote(R.string.caption3, R.string.person3, R.drawable.image_3),
            Quote(R.string.caption4, R.string.person4, R.drawable.image_4),
            Quote(R.string.caption5, R.string.person5, R.drawable.image_5),
            Quote(R.string.caption6, R.string.person6, R.drawable.image_6),
            Quote(R.string.caption7, R.string.person7, R.drawable.image_7),
            Quote(R.string.caption8, R.string.person8, R.drawable.image_8),
            Quote(R.string.caption9, R.string.person9, R.drawable.image_9),
            Quote(R.string.caption10, R.string.person10, R.drawable.image_10),
        )
    }
}