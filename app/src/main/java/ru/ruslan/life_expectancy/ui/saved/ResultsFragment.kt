package ru.ruslan.life_expectancy.ui.saved

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_results.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import ru.ruslan.life_expectancy.domain.SavedPersonEntity
import ru.ruslan.life_expectancy.ui.SharedViewModel

import ru.ruslan.life_expectancy.R
import ru.ruslan.life_expectancy.ui.AllFragmentNames
import ru.ruslan.life_expectancy.ui.OnNextFragment
import ru.ruslan.life_expectancy.domain.Country
import ru.ruslan.life_expectancy.ui.MainActivity
import kotlin.math.roundToLong

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() =
            ResultsFragment()
    }

    private val viewModel: SharedViewModel by sharedViewModel()
    private lateinit var listener: OnNextFragment
    private var birthDate: String = ""
    private var gender: Boolean = false
    private var country: Country =
        Country(1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("d.M.yyyy")
        val today: String = current.format(formatter)

        results_age.visibility = View.GONE
        results_gender.visibility = View.GONE

        //get country by its name from the list of countries from viewmodel??
        viewModel.getCountry().observe(viewLifecycleOwner,
            Observer { t ->
                country = t
                results_country.text = "${t.countryName} is ranked #${country.rank}"
            })

        viewModel.getGender().observe(viewLifecycleOwner,
            Observer { t -> if (t) gender = true })

        viewModel.getDateOfBirth().observe(viewLifecycleOwner,
            Observer { t ->
                birthDate = t.toString()
                val birthday = LocalDate.parse(t.toString(), formatter)
                val numberOfPassedDays = ChronoUnit.DAYS.between(birthday, current)
                results_text.text = "Today ($today) is your ${numberOfPassedDays}th day!"
                val expectedAge = if (gender) country.maleYears else country.femaleYears
                results_years_left.text =
                    "You are expected to live $expectedAge years in your country"
                val expectedDaysFromAge = expectedAge.toLong() * 365.2425
                val numberOfDaysLeft = expectedDaysFromAge - numberOfPassedDays
                results_days_left.text =
                    "i.e. you have approx ${numberOfDaysLeft.toInt()} more days (${(numberOfDaysLeft / 365.2425).roundToLong()} years) to live"
                val expected_date = birthday.plusYears(expectedAge.toLong())
                results_date_of_death.text =
                    "That means you are expected to live at least till ${expected_date.year}"
            })

        save_button.setOnClickListener {
            viewModel.insert(
                SavedPersonEntity(
                    edit_text_name.text.toString(),
                    birthDate,
                    gender,
                    country.countryName
                )
            )
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            if (activity != null && activity is MainActivity)
                (activity as MainActivity).navigationView.selectedItemId =
                    R.id.list_of_saved_persons
            listener.onNextFragment(AllFragmentNames.SAVED, false)
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
}
