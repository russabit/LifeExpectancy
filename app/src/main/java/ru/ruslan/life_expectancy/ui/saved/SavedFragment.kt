package ru.ruslan.life_expectancy.ui.saved

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_saved.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import ru.ruslan.life_expectancy.domain.SavedPersonEntity
import ru.ruslan.life_expectancy.ui.SharedViewModel
import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.ui.AllFragmentNames
import ru.ruslan.life_expectancy.ui.MainActivity
import ru.ruslan.life_expectancy.ui.OnNextFragment


class SavedFragment : Fragment(), SavedPersonAdapter.OnViewListener {
    private lateinit var listener: OnNextFragment
    private lateinit var adapter: SavedPersonAdapter
    private val viewModel: SharedViewModel by sharedViewModel()

    companion object {
        fun newInstance() =
            SavedFragment()
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

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerview_savedpersons)

        viewModel.allPersons.observe(viewLifecycleOwner, Observer { person ->
            // Update the cached copy of the words in the adapter.
            person?.let { adapter.setPersons(it) }
        })

        fab.setOnClickListener {
            if (activity != null && activity is MainActivity)
                (activity as MainActivity).navigationView.selectedItemId = R.id.new_person
            listener.onNextFragment(AllFragmentNames.GENDER, true)
        }
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
        listener.onNextFragment(AllFragmentNames.SAVEDRESULTS, true)
        val personToSave = adapter.persons[position]
        viewModel.setSavedPerson(personToSave)
    }

    private val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deletePerson(adapter.persons[viewHolder.adapterPosition])
            }
        }

    private fun deletePerson(personEntity: SavedPersonEntity) {
        viewModel.delete(personEntity)
        adapter.notifyDataSetChanged()
    }
}
