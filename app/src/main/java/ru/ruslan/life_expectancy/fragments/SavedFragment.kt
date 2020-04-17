package ru.ruslan.life_expectancy.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_saved.*
import ru.ruslan.life_expectancy.Model.SavedPerson
import ru.ruslan.life_expectancy.Model.SharedViewModel
import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.adapters.SavedPersonAdapter
import timber.log.Timber

class SavedFragment : Fragment(), SavedPersonAdapter.OnViewListener {
    private lateinit var listener: OnNextFragment
    private lateinit var adapter: SavedPersonAdapter
    private val viewModel: SharedViewModel by activityViewModels()

    companion object {
        fun newInstance() = SavedFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SavedPersonAdapter(this)
        recyclerview_savedpersons.adapter = adapter
        recyclerview_savedpersons.layoutManager = LinearLayoutManager(this.context)

        viewModel.allPersons.observe(viewLifecycleOwner, Observer { person ->
            // Update the cached copy of the words in the adapter.
            person?.let { adapter.setPersons(it) }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNextFragment) {
            listener = context
        } else {
            throw ClassCastException(
                "$context must implement OnNextFragment."
            )
        }
    }

    override fun onViewClick(position: Int) {
        listener.onNextFragment(AllFragmentNames.SAVEDRESULTS)
        val personToSave = adapter.persons[position]
        viewModel.setSavedPerson(personToSave)
    }
}
