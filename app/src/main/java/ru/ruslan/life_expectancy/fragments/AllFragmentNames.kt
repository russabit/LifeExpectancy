package ru.ruslan.life_expectancy.fragments

import androidx.fragment.app.Fragment

fun fragmentFactory(fragmentName: AllFragmentNames): Fragment {
    return when (fragmentName) {
        AllFragmentNames.COUNTRY -> CountryFragment.newInstance()
        AllFragmentNames.GENDER -> GenderFragment.newInstance()
        AllFragmentNames.AGE -> AgeFragment.newInstance()
        AllFragmentNames.RESULT -> ResultsFragment.newInstance()
        AllFragmentNames.WELCOME -> WelcomeFragment.newInstance()
        AllFragmentNames.SAVED -> SavedFragment.newInstance()
        AllFragmentNames.FAQ -> FaqFragment.newInstance()
    }
}

enum class AllFragmentNames {
    GENDER, AGE, COUNTRY, RESULT, WELCOME, SAVED, FAQ
}

interface OnNextFragment {
    fun onNextFragment(fragmentName: AllFragmentNames)
}