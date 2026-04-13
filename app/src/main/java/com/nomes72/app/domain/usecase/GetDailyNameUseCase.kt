package com.nomes72.app.domain.usecase

import android.content.Context
import com.nomes72.app.R
import com.nomes72.app.domain.model.DailyInsight
import com.nomes72.app.domain.repository.SacredNameRepository
import com.nomes72.app.domain.util.AngelCalendar
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetDailyNameUseCase @Inject constructor(
    private val repository: SacredNameRepository,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(date: LocalDate = LocalDate.now()): DailyInsight {
        val nameNumber = AngelCalendar.getAngelNumberByDate(date)
        val sacredName = repository.getNameOfDay(nameNumber)
        val formatter = DateTimeFormatter.ofPattern("dd/MM")

        return DailyInsight(
            date = date,
            sacredName = sacredName,
            reason = context.getString(R.string.daily_reason, date.format(formatter)),
            intention = context.getString(R.string.daily_intention, sacredName.meaning, sacredName.transliteration)
        )
    }
}