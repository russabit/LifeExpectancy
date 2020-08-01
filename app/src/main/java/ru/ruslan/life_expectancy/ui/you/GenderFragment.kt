package ru.ruslan.life_expectancy.ui.you

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_gender.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.ui.AllFragmentNames
import ru.ruslan.life_expectancy.ui.OnNextFragment
import ru.ruslan.life_expectancy.ui.SharedViewModel

class GenderFragment : Fragment() {

    private lateinit var listener: OnNextFragment
    private val viewModel: SharedViewModel by sharedViewModel()

    companion object {
        fun newInstance() =
            GenderFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gender, container, false)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        button_else.visibility = View.GONE

        button_female.setOnClickListener {
            viewModel.setGender(false)
            listener.onNextFragment(AllFragmentNames.AGE,true)
        }
        button_male.setOnClickListener {
            viewModel.setGender(true)
            listener.onNextFragment(AllFragmentNames.AGE,true)
        }
    }
}
