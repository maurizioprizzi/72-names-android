package com.nomes72.app

import com.nomes72.app.data.local.entity.SacredNameEntity
import com.nomes72.app.domain.model.SacredName
import org.junit.Assert.assertEquals
import org.junit.Test

class SacredNameEntityTest {

    @Test
    fun `toDomain converts correctly`() {
        val entity = SacredNameEntity(
            number = 1,
            hebrewLetters = "והו",
            transliteration = "Vav He Vav",
            meaning = "Centelha Divina",
            meditation = "Medite...",
            torahVerse = "Êxodo 14:19-21",
            attributes = "Iniciativa||Criação||Alinhamento espiritual",
            angelName = "Vehuiah"
        )

        val domain = entity.toDomain()

        assertEquals(1, domain.number)
        assertEquals("והו", domain.hebrewLetters)
        assertEquals(3, domain.attributes.size)
        assertEquals("Iniciativa", domain.attributes[0])
        assertEquals("Criação", domain.attributes[1])
        assertEquals("Alinhamento espiritual", domain.attributes[2])
    }

    @Test
    fun `fromDomain converts correctly`() {
        val domain = SacredName(
            number = 27,
            hebrewLetters = "ירת",
            transliteration = "Yod Resh Tav",
            meaning = "Discernimento no Silêncio",
            meditation = "Mergulhe...",
            torahVerse = "Êxodo 14:19-21",
            attributes = listOf("Discernimento", "Sabedoria", "Consciência"),
            angelName = "Yerathel"
        )

        val entity = SacredNameEntity.fromDomain(domain)

        assertEquals(27, entity.number)
        assertEquals("Discernimento||Sabedoria||Consciência", entity.attributes)
    }

    @Test
    fun `roundtrip domain to entity to domain preserves data`() {
        val original = SacredName(
            number = 42,
            hebrewLetters = "מיך",
            transliteration = "Mem Yod Kaf",
            meaning = "Luz nas Sombras",
            meditation = "Visualize luz pura...",
            torahVerse = "Êxodo 14:19-21",
            attributes = listOf("Resiliência", "Transformação", "Força interior"),
            angelName = "Mikael"
        )

        val converted = SacredNameEntity.fromDomain(original).toDomain()

        assertEquals(original, converted)
    }

    @Test
    fun `empty attributes list converts correctly`() {
        val domain = SacredName(
            number = 1,
            hebrewLetters = "והו",
            transliteration = "Vav He Vav",
            meaning = "Test",
            meditation = "Test",
            torahVerse = "Test",
            attributes = emptyList(),
            angelName = ""
        )

        val entity = SacredNameEntity.fromDomain(domain)
        assertEquals("", entity.attributes)
    }
}