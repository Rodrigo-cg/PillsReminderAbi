package com.abi.pillsreminderabi.data.data_source
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import kotlinx.coroutines.flow.Flow

@Dao
interface PillsHorarioDao {
    @Query("SELECT * FROM HorarioPastilla")
    fun getHorarios(): Flow<List<HorarioPastilla>>
    @Query("SELECT * FROM horariopastilla WHERE id= :id")
    suspend fun getHorarioById(id:Int):HorarioPastilla?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHorario(horarioPastilla: HorarioPastilla)

    @Delete
    suspend fun deleteHorario(horarioPastilla: HorarioPastilla)
}