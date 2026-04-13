package com.nomes72.app

import com.nomes72.app.domain.model.SacredName
import com.nomes72.app.domain.repository.SacredNameRepository
import com.nomes72.app.domain.usecase.GetNameByNumberUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GetNameByNumberUseCaseTest {

    private val fakeName = SacredName(
        number = 27,
        hebrewLetters = "ירת",
        transliteration = "Yod Resh Tav",
        meaning = "Discernimento no Silêncio",
        meditation = "Mergulhe no silêncio profundo...",
        torahVerse = "Êxodo 14:19-21",
        attributes = listOf("Discernimento", "Sabedoria", "Consciência"),
        angelName = "Yerathel"
    )

    private val fakeRepository = object : SacredNameRepository {
        override fun getAllNames(): Flow<List<SacredName>> = flowOf(listOf(fakeName))
        override suspend fun getNameByNumber(number: Int): SacredName? {
            return if (number == 27) fakeName else null
        }
        override suspend fun getNameOfDay(nameNumber: Int): SacredName = fakeName
    }

    private val useCase = GetNameByNumberUseCase(fakeRepository)

    @Test
    fun `returns name when number exists`() = runTest {
        val result = useCase(27)
        assertEquals(fakeName, result)
    }

    @Test
    fun `returns null when number does not exist`() = runTest {
        val result = useCase(1)
        assertNull(result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws exception for number below 1`() = runTest {
        useCase(0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws exception for number above 72`() = runTest {
        useCase(73)
    }
}