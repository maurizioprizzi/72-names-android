package com.nomes72.app.domain.usecase

import com.nomes72.app.domain.model.SacredName
import com.nomes72.app.domain.repository.SacredNameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNamesUseCase @Inject constructor(
    private val repository: SacredNameRepository
) {
    operator fun invoke(): Flow<List<SacredName>> {
        return repository.getAllNames()
    }
}