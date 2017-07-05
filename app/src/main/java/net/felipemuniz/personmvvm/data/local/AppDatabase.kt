package net.felipemuniz.personmvvm.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import net.felipemuniz.personmvvm.domain.entities.Person

/**
 * Created by Muniz on 05/07/2017.
 */

@Database(entities = arrayOf(Person::class), version = 4)
abstract class AppDatabase : RoomDatabase() {

    abstract fun PersonDao(): PersonDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "person_db").build()
            }
            return INSTANCE!!
        }
    }

}