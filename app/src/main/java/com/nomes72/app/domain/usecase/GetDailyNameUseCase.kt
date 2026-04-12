package com.nomes72.app.domain.usecase

import com.nomes72.app.domain.model.DailyInsight
import com.nomes72.app.domain.repository.SacredNameRepository
import com.nomes72.app.domain.util.AngelCalendar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetDailyNameUseCase @Inject constructor(
    private val repository: SacredNameRepository
) {
    suspend operator fun invoke(date: LocalDate = LocalDate.now()): DailyInsight {
        val nameNumber = AngelCalendar.getAngelNumberByDate(date)
        val sacredName = repository.getNameOfDay(nameNumber)
        val formatter = DateTimeFormatter.ofPattern("dd/MM")

        return DailyInsight(
            date = date,
            sacredName = sacredName,
            reason = "Nome regente do período que inclui ${date.format(formatter)}",
            intention = "Medite sobre ${sacredName.meaning} e conecte-se " +
                    "com a energia de ${sacredName.transliteration}."
        )
    }
}