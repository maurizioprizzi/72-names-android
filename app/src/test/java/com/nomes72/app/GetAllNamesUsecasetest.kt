package com.nomes72.app

import com.nomes72.app.domain.model.SacredName
import com.nomes72.app.domain.repository.SacredNameRepository
import com.nomes72.app.domain.usecase.GetAllNamesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllNamesUseCaseTest {

    private fun createFakeName(number: Int) = SacredName(
        number = number,
        hebrewLetters = "והו",
        transliteration = "Vav He Vav",
        meaning = "Test $number",
        meditation = "Meditation $number",
        torahVerse = "Êxodo 14:19-21",
        attributes = listOf("Attr1", "Attr2"),
        angelName = "Angel$number"
    )

    private val fakeNames = (1..72).map { createFakeName(it) }

    private val fakeRepository = object : SacredNameRepository {
        override fun getAllNames(): Flow<List<SacredName>> = flowOf(fakeNames)
        override suspend fun getNameByNumber(number: Int): SacredName? = fakeNames.find { it.number == number }
        override suspend fun getNameOfDay(nameNumber: Int): SacredName = fakeNames[nameNumber - 1]
    }

    private val useCase = GetAllNamesUseCase(fakeRepository)

    @Test
    fun `returns all 72 names`() = runTest {
        val result = useCase().first()
        assertEquals(72, result.size)
    }

    @Test
    fun `names are in correct order`() = runTest {
        val result = useCase().first()
        assertEquals(1, result.first().number)
        assertEquals(72, result.last().number)
    }

    @Test
    fun `empty repository returns empty list`() = runTest {
        val emptyRepo = object : SacredNameRepository {
            override fun getAllNames(): Flow<List<SacredName>> = flowOf(emptyList())
            override suspend fun getNameByNumber(number: Int): SacredName? = null
            override suspend fun getNameOfDay(nameNumber: Int): SacredName =
                throw IllegalStateException("No names")
        }
        val emptyUseCase = GetAllNamesUseCase(emptyRepo)
        val result = emptyUseCase().first()
        assertEquals(0, result.size)
    }
}