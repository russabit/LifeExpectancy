package ru.ruslan.life_expectancy.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ru.ruslan.life_expectancy.R

class SexFragment : Fragment() {

    companion object {
        fun newInstance() = SexFragment()
    }

    private lateinit var viewModel: SexViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sex, container, false)
    }

/*    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SexViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

}
