package ru.ruslan.life_expectancy.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_listitem_savedperson.view.*
import ru.ruslan.life_expectancy.Model.SavedPerson
import ru.ruslan.life_expectancy.R
import timber.log.Timber

class SavedPersonAdapter(
    private val onViewListener: OnViewListener
) :
    RecyclerView.Adapter<SavedPersonAdapter.ViewHolder>() {

    private var persons = emptyList<SavedPerson>()

    inner class ViewHolder(itemView: View, var onViewListener: OnViewListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.image_savedperson
        val name: TextView = itemView.name_savedperson
        val birthDate: TextView = itemView.birthdate_savedperson

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Timber.d("Clicked from inside ViewHolder!")
            onViewListener.onViewClick(adapterPosition)
        }
    }

    internal fun setPersons(persons: List<SavedPerson>) {
        this.persons = persons
        notifyDataSetChanged()
    }

    interface OnViewListener {
        fun onViewClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.rv_listitem_savedperson)
        return ViewHolder(inflatedView, onViewListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPerson = persons[position]
        holder.name.text = currentPerson.name
        holder.birthDate.text = currentPerson.birthday
        holder.imageView.setImageResource(if (currentPerson.gender) R.drawable.ic_person_black_24dp else R.drawable.ic_person_outline_black_24dp)
    }

    override fun getItemCount(): Int = persons.size
}