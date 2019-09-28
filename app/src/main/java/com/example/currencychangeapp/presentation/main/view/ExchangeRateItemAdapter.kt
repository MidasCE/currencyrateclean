package com.example.currencychangeapp.presentation.main.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencychangeapp.R
import com.example.currencychangeapp.presentation.main.model.ExchangeRateItemViewModel

class ExchangeRateItemAdapter : RecyclerView.Adapter<ExchangeRateItemAdapter.ViewHolder>() {

    var exchangeRateItemList : List<ExchangeRateItemViewModel> = mutableListOf()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.curreny_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        return ViewHolder(
            itemLayoutView
        )
    }

    override fun getItemCount(): Int = exchangeRateItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.currencyCodeTextView.text = exchangeRateItemList[position].currencyCode
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currencyCodeTextView: TextView = itemView.findViewById(R.id.currencyCodeTextView)
        val currencyNameTextView: TextView = itemView.findViewById(R.id.currencyNameTextView)
        val currencyRateEditText: EditText = itemView.findViewById(R.id.currencyRateEditText)
    }

}
