package ua.dimaevseenko.mornhousetestapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.dimaevseenko.mornhousetestapp.db.dao.FactDao
import ua.dimaevseenko.mornhousetestapp.model.Fact

@Database(entities = [Fact::class], version = 1)
abstract class FactDB: RoomDatabase(){
   abstract fun getFactDao(): FactDao
}