package com.nomes72.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nomes72.app.data.local.entity.SacredNameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SacredNameDao {

    @Query("SELECT * FROM sacred_names ORDER BY number ASC")
    fun getAllNames(): Flow<List<SacredNameEntity>>

    @Query("SELECT * FROM sacred_names WHERE number = :number")
    suspend fun getByNumber(number: Int): SacredNameEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(names: List<SacredNameEntity>)

    @Query("SELECT COUNT(*) FROM sacred_names")
    suspend fun count(): Int
}