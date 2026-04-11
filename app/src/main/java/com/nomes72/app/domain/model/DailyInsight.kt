package com.nomes72.app.domain.model

import java.time.LocalDate

/**
 * Representa a sugestão de meditação para um dia específico
 * baseada no calendário cabalístico.
 *
 * @param date Data do insight
 * @param sacredName O nome sagrado sugerido para o dia
 * @param reason Explicação de por que este nome é indicado para este dia
 * @param intention Intenção de meditação para o dia
 */
data class DailyInsight(
    val date: LocalDate,
    val sacredName: SacredName,
    val reason: String,
    val intention: String
)
