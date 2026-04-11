package com.nomes72.app.domain.model

import java.time.LocalDate
import java.time.LocalTime

/**
 * Perfil do usuário para cálculo de insights personalizados
 * baseados na data e hora de nascimento.
 *
 * @param birthDate Data de nascimento
 * @param birthTime Hora de nascimento (opcional — refina os cálculos)
 * @param birthPlace Local de nascimento (opcional)
 * @param preferredLanguage Código do idioma preferido (pt, en, es, fr, it, de)
 */
data class UserProfile(
    val birthDate: LocalDate,
    val birthTime: LocalTime? = null,
    val birthPlace: String? = null,
    val preferredLanguage: String = "pt"
)
