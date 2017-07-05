package net.felipemuniz.personmvvm.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import net.felipemuniz.personmvvm.domain.entities.Person;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Muniz on 05/07/2017.
 */


@Dao
public interface PersonDao {
    @Query("select * from Person")
    LiveData<List<Person>> getAllPerson();

    @Query("select * from Person where id = :id")
    Person getPersonbyId(String id);

    @Insert(onConflict = REPLACE)
    void addPerson(Person person);

    @Delete
    void deletePerson(Person person);
}