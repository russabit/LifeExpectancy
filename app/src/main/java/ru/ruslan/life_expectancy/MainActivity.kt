package ru.ruslan.life_expectancy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
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
                .add(R.id.fragment_container, fragmentFactory(AllFragmentNames.WELCOME))
                .commit()
        }

        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.new_person -> {
                    onNextFragment(AllFragmentNames.GENDER)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.list_of_saved_persons -> {
                    onNextFragment(AllFragmentNames.SAVED)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.faq -> {
                    onNextFragment(AllFragmentNames.FAQ)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false

        }

    }

    override fun onNextFragment(fragmentName: AllFragmentNames) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentFactory(fragmentName))
                .addToBackStack(null)
                .commit()
    }
}
