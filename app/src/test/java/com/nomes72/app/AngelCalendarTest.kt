package com.nomes72.app

import com.nomes72.app.domain.util.AngelCalendar
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class AngelCalendarTest {

    @Test
    fun `first day of cycle returns name 1`() {
        val date = LocalDate.of(2026, 3, 21)
        assertEquals(1, AngelCalendar.getAngelNumberByDate(date))
    }

    @Test
    fun `last day of cycle returns name 72`() {
        val date = LocalDate.of(2026, 3, 20)
        assertEquals(72, AngelCalendar.getAngelNumberByDate(date))
    }

    @Test
    fun `march 25 returns name 1`() {
        val date = LocalDate.of(2026, 3, 25)
        assertEquals(1, AngelCalendar.getAngelNumberByDate(date))
    }

    @Test
    fun `march 26 returns name 2`() {
        val date = LocalDate.of(2026, 3, 26)
        assertEquals(2, AngelCalendar.getAngelNumberByDate(date))
    }

    @Test
    fun `august 6 returns name 27 (Yerathel)`() {
        val date = LocalDate.of(1971, 8, 6)
        assertEquals(27, AngelCalendar.getAngelNumberByDate(date))
    }

    @Test
    fun `august 2 returns name 27`() {
        val date = LocalDate.of(2026, 8, 2)
        assertEquals(27, AngelCalendar.getAngelNumberByDate(date))
    }

    @Test
    fun `august 7 returns name 28`() {
        val date = LocalDate.of(2026, 8, 7)
        assertEquals(28, AngelCalendar.getAngelNumberByDate(date))
    }

    @Test
    fun `january 1 returns name 57`() {
        val date = LocalDate.of(2026, 1, 1)
        assertEquals(57, AngelCalendar.getAngelNumberByDate(date))
    }

    @Test
    fun `december 31 returns name 56`() {
        val date = LocalDate.of(2026, 12, 31)
        assertEquals(56, AngelCalendar.getAngelNumberByDate(date))
    }

    @Test
    fun `april 12 returns name 5 (Mahasiah)`() {
        val date = LocalDate.of(2026, 4, 12)
        assertEquals(5, AngelCalendar.getAngelNumberByDate(date))
    }

    @Test
    fun `february 29 leap year has valid result`() {
        val date = LocalDate.of(2024, 2, 29)
        val result = AngelCalendar.getAngelNumberByDate(date)
        assert(result in 1..72) { "Result $result is not in range 1-72" }
    }

    @Test
    fun `all days of year return valid range`() {
        val year = 2026
        var date = LocalDate.of(year, 1, 1)
        val endDate = LocalDate.of(year, 12, 31)

        while (!date.isAfter(endDate)) {
            val result = AngelCalendar.getAngelNumberByDate(date)
            assert(result in 1..72) {
                "Date $date returned $result, expected 1-72"
            }
            date = date.plusDays(1)
        }
    }

    @Test
    fun `consecutive periods have no gaps`() {
        val year = 2026
        var date = LocalDate.of(year, 1, 1)
        val endDate = LocalDate.of(year, 12, 31)
        val coveredNumbers = mutableSetOf<Int>()

        while (!date.isAfter(endDate)) {
            coveredNumbers.add(AngelCalendar.getAngelNumberByDate(date))
            date = date.plusDays(1)
        }

        assertEquals("Not all 72 names are covered", 72, coveredNumbers.size)
    }
}