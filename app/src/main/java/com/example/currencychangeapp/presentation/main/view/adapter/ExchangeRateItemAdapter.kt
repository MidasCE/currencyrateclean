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
    private val onBaseExchangeDetailChangedListener: OnBaseExchangeDetailChangedListener,
    private val currencyStringMapper: CurrencyStringMapper,
    private val currencyFlagResourceMapper: CurrencyFlagResourceMapper
) : RecyclerView.Adapter<ExchangeRateItemAdapter.ViewHolder>() {

    var exchangeRatePositionList: MutableList<String> = mutableListOf()
    private var exchangeRateItemMap: MutableMap<String, ExchangeRateItemViewModel> = mutableMapOf()

    fun updateRateItem(list: List<ExchangeRateItemViewModel>) {
        if (exchangeRatePositionList.isEmpty()) {
            exchangeRatePositionList.addAll(list.map { it.currencyCode })
        }

        list.forEach {
            exchangeRateItemMap[it.currencyCode] = it
        }
    }

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

   // override fun getItemId(position: Int): Long {
  //      return position.toLong()
  //  }

    override fun getItemCount(): Int = exchangeRatePositionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rateItem = exchangeRateItemMap[exchangeRatePositionList[position]]
        rateItem?.let { viewModel ->
            holder.bindViewHolder(
                viewModel
            )
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val currencyCodeTextView: TextView =
            itemView.findViewById(R.id.currencyCodeTextView)
        private val currencyNameTextView: TextView =
            itemView.findViewById(R.id.currencyNameTextView)
        private val currencyFlagImageView: ImageView = itemView.findViewById(R.id.currencyFlag)
        val currencyAmountEditText: EditText = itemView.findViewById(R.id.currencyAmountEditText)
        var currencyCode: String = ""

        fun bindViewHolder(viewModel: ExchangeRateItemViewModel) {
            if (currencyCode != viewModel.currencyCode) {
                initView(viewModel)
                currencyCode = viewModel.currencyCode
            }

            if (!currencyAmountEditText.isFocused) {
                currencyAmountEditText.setText(viewModel.exchangeAmount.toString())
            }
        }

        private fun initView(viewModel: ExchangeRateItemViewModel) {
            val currencyCode = viewModel.currencyCode
            currencyCodeTextView.text = currencyCode
            currencyNameTextView.text = currencyStringMapper.getCurrencyName(
                currencyCode.toLowerCase(
                    Locale.ENGLISH
                )
            )

            currencyFlagImageView
                .setImageResource(
                    currencyFlagResourceMapper.getCurrencyFlagResId(
                        currencyCode.toLowerCase(
                            Locale.ENGLISH
                        )
                    )
                )

            currencyAmountEditText.setOnFocusChangeListener { view, hasFocus ->
                if (view == currencyAmountEditText && hasFocus) {
                    layoutPosition.takeIf { it > 0 }?.also { currentPosition ->

                        exchangeRatePositionList.removeAt(currentPosition).also {
                            exchangeRatePositionList.add(0, it)
                        }
                        notifyItemMoved(currentPosition, 0)
                        onBaseExchangeDetailChangedListener.onAmountChanged(
                            viewModel.currencyCode,
                            viewModel.exchangeAmount
                        )
                    }
                }
            }

            currencyAmountEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                @Synchronized
                override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (currencyAmountEditText.isFocused) {
                        charSequence?.takeIf { it.isNotEmpty() }?.let {
                            onBaseExchangeDetailChangedListener.onAmountChanged(
                                currencyCode,
                                it.toString().toFloat()
                            )
                        }
                    }
                }
            }

            )
        }
    }

}
