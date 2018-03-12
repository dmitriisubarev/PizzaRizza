package ru.efirus.app.pizzarizza;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by efirus on 12.03.18.
 */
@Database(entities = {Basket.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BasketDao basketDao();
}

