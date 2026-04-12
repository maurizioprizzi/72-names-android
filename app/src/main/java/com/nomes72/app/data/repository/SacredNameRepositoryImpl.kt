package com.nomes72.app.data.repository

import android.content.Context
import com.nomes72.app.data.local.dao.SacredNameDao
import com.nomes72.app.data.local.entity.SacredNameEntity
import com.nomes72.app.domain.model.SacredName
import com.nomes72.app.domain.repository.SacredNameRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Serializable
private data class SacredNameJson(
    val number: Int,
    val hebrewLetters: String,
    val transliteration: String,
    val meaning: String,
    val meditation: String,
    val torahVerse: String,
    val attributes: List<String>,
    val angelName: String = ""
)

@Singleton
class SacredNameRepositoryImpl @Inject constructor(
    private val dao: SacredNameDao,
    @ApplicationContext private val context: Context
) : SacredNameRepository {

    private val json = Json { ignoreUnknownKeys = true }

    override fun getAllNames(): Flow<List<SacredName>> {
        return flow {
            ensureDataLoaded()
            emitAll(dao.getAllNames().map { entities ->
                entities.map { it.toDomain() }
            })
        }
    }

    override suspend fun getNameByNumber(number: Int): SacredName? {
        ensureDataLoaded()
        return dao.getByNumber(number)?.toDomain()
    }

    override suspend fun getNameOfDay(nameNumber: Int): SacredName {
        ensureDataLoaded()
        return dao.getByNumber(nameNumber)?.toDomain()
            ?: throw IllegalStateException("Nome $nameNumber não encontrado no banco")
    }

    private suspend fun ensureDataLoaded() {
        if (dao.count() > 0) return

        val languageCode = context.resources.configuration.locales[0].language
        val fileName = getJsonFileName(languageCode)

        val jsonString = context.assets
            .open(fileName)
            .bufferedReader()
            .use { it.readText() }

        val namesFromJson = json.decodeFromString<List<SacredNameJson>>(jsonString)

        val entities = namesFromJson.map { nameJson ->
            SacredNameEntity(
                number = nameJson.number,
                hebrewLetters = nameJson.hebrewLetters,
                transliteration = nameJson.transliteration,
                meaning = nameJson.meaning,
                meditation = nameJson.meditation,
                torahVerse = nameJson.torahVerse,
                attributes = nameJson.attributes.joinToString("||"),
                angelName = nameJson.angelName
            )
        }

        dao.insertAll(entities)
    }

    /**
     * Retorna o nome do arquivo JSON baseado no idioma do dispositivo.
     * Fallback para português se o idioma não for suportado.
     */
    private fun getJsonFileName(languageCode: String): String {
        return when (languageCode) {
            "pt" -> "sacred_names_pt.json"
            "en" -> "sacred_names_en.json"
            "es" -> "sacred_names_es.json"
            "fr" -> "sacred_names_fr.json"
            "it" -> "sacred_names_it.json"
            "de" -> "sacred_names_de.json"
            else -> "sacred_names_en.json"
        }
    }
}