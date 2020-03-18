package ru.ruslan.life_expectancy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ruslan.life_expectancy.fragments.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), OnNextFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragmentFactory(AllFragments.GENDER))
                .commit()
        }
    }

    override fun OnNextFragment(fragmentName: AllFragments) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragmentFactory(fragmentName))
            .addToBackStack(null)
            .commit()
    }



}
