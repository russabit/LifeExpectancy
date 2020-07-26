package ru.ruslan.life_expectancy.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.domain.Country
import timber.log.Timber

class CountriesRecyclerAdapter(
    val context: Context?,
    var countries: ArrayList<Country>,
    private val mOnViewListener: OnViewListener
) :
    RecyclerView.Adapter<CountriesRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.rv_listitem_country, false)
        return ViewHolder(inflatedView, mOnViewListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.country.text = countries[position].countryName
    }

    override fun getItemCount(): Int = countries.size

    //to get data to search Category
    fun filterList(filteredCountriesList: ArrayList<Country>) {
        this.countries = filteredCountriesList;
        notifyDataSetChanged()
    }

    class ViewHolder(v: View, var onViewListener: OnViewListener) : RecyclerView.ViewHolder(v),
        View.OnClickListener {
        var country: TextView

        init {
            v.setOnClickListener(this) //this?
            country = itemView.findViewById(R.id.country_name)
        }

        override fun onClick(v: View?) {
            Timber.d("Clicked from inside ViewHolder!")
            onViewListener.onViewClick(adapterPosition)
        }

    }

    //better way to implement onClick:
    interface OnViewListener {
        fun onViewClick(position: Int)
    }

}

fun ViewGroup.inflate(
    @LayoutRes layoutRes: Int,
    attachToRoot: Boolean = false
): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
