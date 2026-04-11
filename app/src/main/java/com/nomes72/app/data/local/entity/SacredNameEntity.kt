package com.nomes72.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nomes72.app.domain.model.SacredName

@Entity(tableName = "sacred_names")
data class SacredNameEntity(
    @PrimaryKey
    val number: Int,
    val hebrewLetters: String,
    val transliteration: String,
    val meaning: String,
    val meditation: String,
    val torahVerse: String,
    val attributes: String,  // JSON string — TypeConverter converte
    val angelName: String
) {
    fun toDomain(): SacredName {
        return SacredName(
            number = number,
            hebrewLetters = hebrewLetters,
            transliteration = transliteration,
            meaning = meaning,
            meditation = meditation,
            torahVerse = torahVerse,
            attributes = attributes.split("||"),
            angelName = angelName
        )
    }

    companion object {
        fun fromDomain(domain: SacredName): SacredNameEntity {
            return SacredNameEntity(
                number = domain.number,
                hebrewLetters = domain.hebrewLetters,
                transliteration = domain.transliteration,
                meaning = domain.meaning,
                meditation = domain.meditation,
                torahVerse = domain.torahVerse,
                attributes = domain.attributes.joinToString("||"),
                angelName = domain.angelName
            )
        }
    }
}