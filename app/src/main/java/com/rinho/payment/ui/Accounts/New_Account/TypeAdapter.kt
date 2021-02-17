package com.rinho.payment.ui.Accounts.New_Account

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * Dropdown list adapter of gender that return [Gender.value] as item id.
 */
class TypeAdapter(context: Context, genders: List<Type>) : ArrayAdapter<Type>(
    context,
    android.R.layout.simple_list_item_1,
    android.R.id.text1,
    genders
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getView(position, convertView, parent) as TextView
        textView.setText(getItem(position)!!.txtRes)
        return textView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getView(position, convertView, parent) as TextView
        textView.setText(getItem(position)!!.txtRes)
        return textView
    }

    override fun getItemId(position: Int) = getItem(position)!!.value.toLong()
}