package ru.ruslan.life_expectancy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ru.ruslan.life_expectancy.R

class GenderFragment : Fragment() {

    companion object {
        fun newInstance() = GenderFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gender, container, false)
    }

}
