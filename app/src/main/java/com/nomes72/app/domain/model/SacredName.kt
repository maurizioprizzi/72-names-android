package com.nomes72.app.domain.model

/**
 * Representa um dos 72 Nomes Sagrados da Cabala.
 *
 * @param number Número sequencial do nome (1 a 72)
 * @param hebrewLetters As três letras hebraicas que formam o nome
 * @param transliteration Transliteração fonética para o alfabeto latino
 * @param meaning Significado espiritual do nome
 * @param meditation Texto de meditação associado
 * @param torahVerse Versículo da Torá de onde o nome é derivado
 * @param attributes Lista de atributos e qualidades espirituais do nome
 * @param angelName Nome do anjo associado (quando aplicável)
 */
data class SacredName(
    val number: Int,
    val hebrewLetters: String,
    val transliteration: String,
    val meaning: String,
    val meditation: String,
    val torahVerse: String,
    val attributes: List<String>,
    val angelName: String = ""
)