package com.s95ammar.mvpcurrencyconverter.ui.currencieslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.s95ammar.mvpcurrencyconverter.R
import com.s95ammar.mvpcurrencyconverter.ui.currencieslist.entity.RateEntity
import kotlinx.android.synthetic.main.item_rate.view.*

class CurrenciesListAdapter : RecyclerView.Adapter<CurrenciesListAdapter.CurrenciesListViewHolder>() {

    var items = emptyList<RateEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesListViewHolder {
        return CurrenciesListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rate, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CurrenciesListViewHolder, position: Int) {
        items.getOrNull(position)?.let { holder.bind(it) }
    }

    inner class CurrenciesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: RateEntity) {
            itemView.list_from_code_text_view.text = item.fromCode
            itemView.list_from_name_text_view.text = item.fromName
            itemView.list_rate_value_text_view.text = itemView.context.getString(R.string.rate_format, item.rate)
        }
    }
}