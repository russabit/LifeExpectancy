package ru.ruslan.life_expectancy.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.ruslan.life_expectancy.Model.SavedPerson

@Database(entities = [SavedPerson::class], version = 1, exportSchema = false)
abstract class PersonRoomDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

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
                    var person = SavedPerson("Ruslan", "14.10.1994", true, "Russia")
                    personDao.insert(person)

                    person = SavedPerson("Sophie", "21.05.1994", false, "New Zealand")
                    personDao.insert(person)

                    person = SavedPerson("Alla", "29.04.1962", false, "Russia")
                    personDao.insert(person)

                    for (i in 1..10) {
                        person = SavedPerson("Alla$i", "29.04.1962", false, "Russia")
                        personDao.insert(person)
                    }

                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: PersonRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PersonRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonRoomDatabase::class.java,
                    "persons"
                )
                    .addCallback(PersonDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}