package com.pradesh.bhaktapursales.utlis

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class BSTextViewBold(context: Context,attrs: AttributeSet): AppCompatTextView(context,attrs) {
    init {
        //call the functions to apply the font to the components
        applyFont()
    }

    private fun applyFont(){
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"Roboto-Regular.ttf")
        setTypeface(typeface)
    }
}