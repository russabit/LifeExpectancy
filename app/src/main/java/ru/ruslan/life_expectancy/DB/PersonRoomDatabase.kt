package ru.ruslan.life_expectancy.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.ruslan.life_expectancy.Model.SavedPersonEntity

@Database(entities = [SavedPersonEntity::class], version = 1, exportSchema = false)
abstract class PersonRoomDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao
}

/*
    private class PersonDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var personDao = database.personDao()

                    // Delete all content here.
                    //personDao.deleteAll()

                    // Add sample persons.
                    var person = SavedPersonEntity("Ruslan", "14.10.1994", true, "Russia")
                    personDao.insert(person)

                    person = SavedPersonEntity("Sophie", "21.05.1994", false, "New Zealand")
                    personDao.insert(person)

                    person = SavedPersonEntity("Alla", "29.04.1962", false, "Russia")
                    personDao.insert(person)

                    for (i in 1..10) {
                        person = SavedPersonEntity("Alla$i", "29.04.1962", false, "Russia")
                        personDao.insert(person)
                    }

                }
            }
        }
    }
*/