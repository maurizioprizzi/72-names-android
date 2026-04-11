package com.nomes72.app.domain.usecase

import com.nomes72.app.domain.model.DailyInsight
import com.nomes72.app.domain.repository.SacredNameRepository
import java.time.LocalDate
import javax.inject.Inject

class GetDailyNameUseCase @Inject constructor(
    private val repository: SacredNameRepository
) {
    suspend operator fun invoke(date: LocalDate = LocalDate.now()): DailyInsight {
        val dayOfYear = date.dayOfYear
        val sacredName = repository.getNameOfDay(dayOfYear)

        return DailyInsight(
            date = date,
            sacredName = sacredName,
            reason = "Nome indicado para o dia ${date.dayOfMonth}/${date.monthValue}" +
                    " — ciclo cabalístico do dia ${dayOfYear % 72 + 1}",
            intention = "Medite sobre ${sacredName.meaning} e abra seu coração " +
                    "para a energia de ${sacredName.transliteration}."
        )
    }
}