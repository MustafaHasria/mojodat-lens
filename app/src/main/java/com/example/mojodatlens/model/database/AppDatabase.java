package com.example.mojodatlens.model.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mojodatlens.model.dao.ItemDao;
import com.example.mojodatlens.model.entity.Item;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        Item.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    //region Dao
    public abstract ItemDao getItemDao();
    //endregion


    // Instantiate DB
    private static volatile AppDatabase INSTANCE;

    private static final RoomDatabase.Callback callback = new Callback() {

        // this override execute only for once database created(at the first time)
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new TaskInitDb(INSTANCE).execute();
        }
    };

    public static AppDatabase getInstance(final Context context) {

        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "MojodatLensDb")
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static class TaskInitDb extends AsyncTask<Void, Void, Void> {
        ItemDao itemDao;

        public TaskInitDb(AppDatabase appDatabase) {
            this.itemDao = appDatabase.getItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.insert(new Item("000001", "Mercedes", "Cars"));
            itemDao.insert(new Item("000002", "meeting table", "Furniture"));
            itemDao.insert(new Item("000003", "Sharp Calculator", "Equipments"));
            itemDao.insert(new Item("000004", "Compressor", "Electrical Equipments"));
            itemDao.insert(new Item("000005", "Powder Fire extinguisher", "Fire extinguisher"));
            itemDao.insert(new Item("000006", "Optas Telephone", "Telephone"));
            itemDao.insert(new Item("000007", "Samsung Printer", "Printer"));
            itemDao.insert(new Item("000008", "MoneyGram Fax", "Fax"));
            itemDao.insert(new Item("000009", "Beta Machine", "Photocopier Machine"));
            itemDao.insert(new Item("000010", "Cassio Calculator", "Calculator"));
            itemDao.insert(new Item("000011", "work station", "Furniture"));
            itemDao.insert(new Item("000012", "Volks Wagen", "Cars"));
            itemDao.insert(new Item("000013", "Surround System", "Electrical Equipments"));
            itemDao.insert(new Item("000014", "Attendance Machine", "Electrical Equipments"));
            itemDao.insert(new Item("000015", "LG LCD 32", "Television & Video"));
            itemDao.insert(new Item("000016", "Smoke Detector", "Alarm System"));
            itemDao.insert(new Item("000017", "CISCO PC", "Computer Machine"));
            itemDao.insert(new Item("000018", "HP PC", "Computer Machine"));
            itemDao.insert(new Item("000019", "HP Printer", "Printer"));
            itemDao.insert(new Item("000020", "Planet Machine", "Planet Machine"));
            itemDao.insert(new Item("000021", "Metal Curtain", "Metal Curtain"));
            itemDao.insert(new Item("000022", "White Curtains", "Curtain"));
            itemDao.insert(new Item("000023", "Meeting Table 220x110x72.2", "Desks & Tables"));
            itemDao.insert(new Item("000024", "Side Table", "Desks & Tables"));
            itemDao.insert(new Item("000025", "Big Wooden Cabinet", "Cabinet"));
            return null;
        }
    }
    //endregion
}
