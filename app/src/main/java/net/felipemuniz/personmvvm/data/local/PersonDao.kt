package net.felipemuniz.personmvvm.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import net.felipemuniz.personmvvm.domain.entities.Person

import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * Created by Muniz on 05/07/2017.
 */


@Dao
interface PersonDao {
    @get:Query("select * from Person")
    val allPerson: LiveData<List<Person>>

    @Query("select * from Person where id = :id")
    fun getPersonbyId(id: String): Person

    @Insert(onConflict = REPLACE)
    fun addPerson(person: Person)

    @Delete
    fun deletePerson(person: Person)
}