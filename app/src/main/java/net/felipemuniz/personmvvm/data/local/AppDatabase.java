package net.felipemuniz.personmvvm.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import net.felipemuniz.personmvvm.domain.entities.Person;

/**
 * Created by Muniz on 05/07/2017.
 */

@Database(entities = {Person.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "person_db").build();
        }
        return INSTANCE;
    }

    public abstract PersonDao PersonDao();

}