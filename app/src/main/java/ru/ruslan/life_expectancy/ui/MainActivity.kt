package ru.ruslan.life_expectancy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.ruslan.life_expectancy.R
import timber.log.Timber

class MainActivity : AppCompatActivity(), OnNextFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragmentFactory(AllFragmentNames.SAVED))
                .commit()
        }

        navigationView.setOnNavigationItemSelectedListener {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            when (it.itemId) {
                R.id.new_person -> {
                    onNextFragment(AllFragmentNames.GENDER, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.list_of_saved_persons -> {
                    onNextFragment(AllFragmentNames.SAVED, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.faq -> {
                    onNextFragment(AllFragmentNames.WELCOME, false)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false

        }

    }

    override fun onNextFragment(fragmentName: AllFragmentNames, saveToBackStack: Boolean) {
        if (saveToBackStack) supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragmentFactory(fragmentName))
            .addToBackStack(null)
            .commit()
        else
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragmentFactory(fragmentName))
            .commit()
    }
}
