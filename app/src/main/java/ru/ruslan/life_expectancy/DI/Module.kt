package ru.ruslan.life_expectancy.DI

import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.ruslan.life_expectancy.DB.PersonRoomDatabase
import ru.ruslan.life_expectancy.Model.SharedViewModel

/*val sampleModule = module {
    single {
        Room.databaseBuilder(
            this.androidApplication(),
            PersonRoomDatabase::class.java,
            "persons"
        )
            .addCallback(PersonRoomDatabase.PersonDatabaseCallback(scope))
            .build()
    }


    factory { SupervisiorJob() }
    factory { CoroutineScope(Dispatchers.IO + get<SupervisorJob>()) }
}*/

val viewModelModule = module {
    viewModel { SharedViewModel(androidApplication()) }
}