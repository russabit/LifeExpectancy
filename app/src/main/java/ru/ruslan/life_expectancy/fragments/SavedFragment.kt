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
    private lateinit var listOfPersons: List<SavedPerson>
    private lateinit var adapter: SavedPersonAdapter
    private val viewModel: SharedViewModel by activityViewModels()

    companion object {
        fun newInstance() = SavedFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getSavedPersons().observe(viewLifecycleOwner, Observer { t ->
            listOfPersons = t
            Timber.d("t is $t")
            val recyclerView = recyclerview_savedpersons
            adapter = SavedPersonAdapter(listOfPersons, this)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = adapter
        })
        return inflater.inflate(R.layout.fragment_saved, container, false)
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
        listener.onNextFragment(AllFragmentNames.RESULT)
    }
}
