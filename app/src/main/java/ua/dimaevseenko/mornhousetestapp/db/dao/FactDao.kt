package ua.dimaevseenko.mornhousetestapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ua.dimaevseenko.mornhousetestapp.model.Fact

@Dao
interface FactDao {

    @Query("select * from Fact")
    fun getFacts(): List<Fact>

    @Insert
    fun insertFact(fact: Fact)
}