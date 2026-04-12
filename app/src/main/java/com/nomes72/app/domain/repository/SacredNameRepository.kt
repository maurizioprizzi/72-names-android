package com.nomes72.app.domain.repository

import com.nomes72.app.domain.model.SacredName
import kotlinx.coroutines.flow.Flow

interface SacredNameRepository {
    fun getAllNames(): Flow<List<SacredName>>
    suspend fun getNameByNumber(number: Int): SacredName?
    suspend fun getNameOfDay(nameNumber: Int): SacredName
}