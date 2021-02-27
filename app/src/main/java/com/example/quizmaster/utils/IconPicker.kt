package com.example.quizmaster.utils

import com.example.quizmaster.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.ic_icon_1,
        R.drawable.ic_icon_2,
        R.drawable.ic_icon_3,
        R.drawable.ic_icon_4,
        R.drawable.ic_icon_5,
        R.drawable.ic_icon_6,
        R.drawable.ic_icon_7,
        R.drawable.ic_icon_8,
    )

    var currentIndex = 0

    fun getIcon(): Int {
        currentIndex = (currentIndex+1)%icons.size
        return icons[currentIndex]
    }
}