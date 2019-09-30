package com.example.currencychangeapp.presentation.main.view.adapter

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencychangeapp.R
import com.example.currencychangeapp.presentation.shared.mapper.CurrencyFlagResourceMapper
import com.example.currencychangeapp.presentation.shared.mapper.CurrencyStringMapper
import com.example.currencychangeapp.presentation.main.model.ExchangeRateItemViewModel
import java.util.*

class ExchangeRateItemAdapter(
    private val OnAmountChangedListener: OnAmountChangedListener,
    private val currencyStringMapper: CurrencyStringMapper,
    private val currencyFlagResourceMapper: CurrencyFlagResourceMapper
) : RecyclerView.Adapter<ExchangeRateItemAdapter.ViewHolder>() {

    var exchangeRateItemList: MutableList<ExchangeRateItemViewModel> = mutableListOf()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(
            itemLayoutView
        )
    }

    override fun getItemCount(): Int = exchangeRateItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currencyCode = exchangeRateItemList[position].currencyCode
        holder.currencyCodeTextView.text = currencyCode
        holder.currencyNameTextView.text = currencyStringMapper.getCurrencyName(
            currencyCode.toLowerCase(
                Locale.ENGLISH
            )
        )

        holder.currencyFlagImageView
            .setImageResource(
                currencyFlagResourceMapper.getCurrencyFlagResId(
                    currencyCode.toLowerCase(
                        Locale.ENGLISH
                    )
                )
            )
        holder.currencyAmountEditText.setText(exchangeRateItemList[position].exchangeAmount.toString())

      

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currencyCodeTextView: TextView = itemView.findViewById(R.id.currencyCodeTextView)
        val currencyNameTextView: TextView = itemView.findViewById(R.id.currencyNameTextView)
        val currencyFlagImageView: ImageView = itemView.findViewById(R.id.currencyFlag)
        val currencyAmountEditText: EditText = itemView.findViewById(R.id.currencyAmountEditText)
    }

}
