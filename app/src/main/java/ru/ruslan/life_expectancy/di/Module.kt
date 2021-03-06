package ru.ruslan.life_expectancy.di

import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.ruslan.life_expectancy.data.PersonRoomDatabase
import ru.ruslan.life_expectancy.ui.SharedViewModel
import ru.ruslan.life_expectancy.data.PersonRepository

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(), //or androidContext()?
            PersonRoomDatabase::class.java, "persons")
            //.addCallback(PersonRoomDatabase.PersonDatabaseCallback(scope))
            .build()
    }

    single { get<PersonRoomDatabase>().personDao() }

    single { PersonRepository(get()) }

//    factory { SupervisiorJob() } //for an onOpen callback?
    factory { CoroutineScope(Dispatchers.IO /*+ get<SupervisorJob>()*/) }
}

val viewModelModule = module {
    viewModel {
        SharedViewModel(
            get(),
            androidApplication()
        )
    }
}