package com.nomes72.app.domain.usecase

import com.nomes72.app.domain.model.SacredName
import com.nomes72.app.domain.repository.SacredNameRepository
import javax.inject.Inject

class GetNameByNumberUseCase @Inject constructor(
    private val repository: SacredNameRepository
) {
    suspend operator fun invoke(number: Int): SacredName? {
        require(number in 1..72) { "Número deve estar entre 1 e 72" }
        return repository.getNameByNumber(number)
    }
}