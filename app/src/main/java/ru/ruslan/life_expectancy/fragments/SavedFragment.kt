package ru.ruslan.life_expectancy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ruslan.life_expectancy.R

class SavedFragment : Fragment() {

    companion object {
        fun newInstance() = SavedFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }
}
