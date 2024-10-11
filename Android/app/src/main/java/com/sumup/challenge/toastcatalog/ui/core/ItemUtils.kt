package com.sumup.challenge.toastcatalog.ui.core

import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.TextViewCompat
import com.sumup.challenge.toastcatalog.R

object ItemUtils {
    fun TextView.setRoundedTextViewStyle() {
        background = AppCompatResources.getDrawable(context, R.drawable.circle)
        setGravity(Gravity.CENTER)
        TextViewCompat.setTextAppearance(this, R.style.RoundedTextView_ItemStyle)
    }
}