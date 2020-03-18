package ru.ruslan.life_expectancy.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_gender.*
import ru.ruslan.life_expectancy.Model.SharedViewModel
import ru.ruslan.life_expectancy.OnNextFragment

import ru.ruslan.life_expectancy.R

class GenderFragment : Fragment() {

    private val model: SharedViewModel by viewModels()

    private lateinit var listener: OnNextFragment

    companion object {
        fun newInstance() = GenderFragment()
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
        button_female.setOnClickListener {
            model.setGender(false)
            listener.OnNextFragment(AllFragments.AGE)}
        button_male.setOnClickListener {
            model.setGender(true)
            listener.OnNextFragment(AllFragments.AGE)}
    }
}
