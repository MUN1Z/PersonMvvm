package net.felipemuniz.personmvvm.presentation.personList;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import net.felipemuniz.personmvvm.data.local.AppDatabase;
import net.felipemuniz.personmvvm.domain.entities.Person;

import java.util.List;

/**
 * Created by Muniz on 05/07/2017.
 */

public class PersonListViewModel extends AndroidViewModel {

    private final LiveData<List<Person>> personList;

    private AppDatabase appDatabase;

    public PersonListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        personList = appDatabase.PersonDao().getAllPerson();
    }

    public LiveData<List<Person>> getAllPerson() {
        return personList;
    }

    public void deletePerson(Person person) {
        new deleteAsyncTask(appDatabase).execute(person);
    }

    private static class deleteAsyncTask extends AsyncTask<Person, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Person... params) {
            db.PersonDao().deletePerson(params[0]);
            return null;
        }

    }

    public void addPerson(final Person person) {
        new addAsyncTask(appDatabase).execute(person);
    }

    private static class addAsyncTask extends AsyncTask<Person, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Person... params) {
            db.PersonDao().addPerson(params[0]);
            return null;
        }

    }

}