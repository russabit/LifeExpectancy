package ru.ruslan.life_expectancy.ui.you

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_age.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.ui.AllFragmentNames
import ru.ruslan.life_expectancy.ui.OnNextFragment
import ru.ruslan.life_expectancy.ui.SharedViewModel

class AgeFragment : Fragment() {

    private lateinit var listener: OnNextFragment

    private val viewModel: SharedViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_age, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        next_button.setOnClickListener {
            viewModel.setDateOfBirth("${datePicker.dayOfMonth}.${datePicker.month + 1}.${datePicker.year}")
            listener.onNextFragment(AllFragmentNames.COUNTRY, true)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AgeFragment()
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
}
