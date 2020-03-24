package ru.ruslan.life_expectancy.fragments

import androidx.fragment.app.Fragment

fun fragmentFactory(allFragments: AllFragments): Fragment {
    return when (allFragments) {
        AllFragments.COUNTRY -> CountryFragment.newInstance()
        AllFragments.GENDER -> GenderFragment.newInstance()
        AllFragments.AGE -> AgeFragment.newInstance()
        AllFragments.RESULT -> ResultsFragment.newInstance()
    }
}

enum class AllFragments {
    GENDER, AGE, COUNTRY, RESULT
}

interface OnNextFragment {
    fun onNextFragment(fragmentName: AllFragments)
}