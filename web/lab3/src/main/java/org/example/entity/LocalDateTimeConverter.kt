package org.example.entity

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
open class LocalDateTimeConverter : AttributeConverter<LocalDateTime, Timestamp> {

    override fun convertToDatabaseColumn(localDateTime: LocalDateTime?): Timestamp? {
        return localDateTime?.let { Timestamp.valueOf(it) }
    }

    override fun convertToEntityAttribute(timestamp: Timestamp?): LocalDateTime? {
        return timestamp?.toLocalDateTime()
    }
}

