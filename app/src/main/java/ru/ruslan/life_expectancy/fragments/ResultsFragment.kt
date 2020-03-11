package ru.ruslan.life_expectancy.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

import ru.ruslan.life_expectancy.R
import timber.log.Timber

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsFragment()
    }

/*    private lateinit var viewModel: ResultsViewModel*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.results_fragment, container, false)
    }

/*    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResultsViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
        var answer: String =  current.format(formatter)

        Timber.d(answer)
    }
}
