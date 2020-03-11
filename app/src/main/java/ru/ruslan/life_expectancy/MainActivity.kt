package ru.ruslan.life_expectancy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ruslan.life_expectancy.fragments.AgeFragment
import ru.ruslan.life_expectancy.fragments.CountryFragment
import ru.ruslan.life_expectancy.fragments.GenderFragment
import ru.ruslan.life_expectancy.fragments.ResultsFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, AgeFragment.newInstance(), "sex")
                .commit()
        }
    }
}
