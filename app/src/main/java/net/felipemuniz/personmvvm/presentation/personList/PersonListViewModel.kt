package net.felipemuniz.personmvvm.presentation.personList

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import net.felipemuniz.personmvvm.data.local.AppDatabase

import net.felipemuniz.personmvvm.domain.entities.Person

/**
 * Created by Muniz on 05/07/2017.
 */

class PersonListViewModel(application: Application) : AndroidViewModel(application) {

    val allPerson: LiveData<List<Person>>

    private val appDatabase: AppDatabase

    init {

        appDatabase = AppDatabase.getDatabase(this.getApplication<Application>())

        allPerson = appDatabase.PersonDao().allPerson
    }

    fun deletePerson(person: Person) {
        deleteAsyncTask(appDatabase).execute(person)
    }

    private class deleteAsyncTask internal constructor(private val db: AppDatabase) : AsyncTask<Person, Void, Void>() {

        override fun doInBackground(vararg params: Person): Void? {
            db.PersonDao().deletePerson(params[0])
            return null
        }

    }

    fun addPerson(person: Person) {
        addAsyncTask(appDatabase).execute(person)
    }

    private class addAsyncTask internal constructor(private val db: AppDatabase) : AsyncTask<Person, Void, Void>() {

        override fun doInBackground(vararg params: Person): Void? {
            db.PersonDao().addPerson(params[0])
            return null
        }

    }

}