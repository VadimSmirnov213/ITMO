package org.example.service

import org.example.dto.PointRequestDto
import org.example.entity.PointEntity
import org.example.repository.ResultsRepository
import org.example.validation.ValidationException
import org.example.validation.XCoordinateValidator
import org.example.validation.YCoordinateValidator
import org.example.validation.RadiusValidator
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
open class PointService {

    @Inject
    private lateinit var xValidator: XCoordinateValidator

    @Inject
    private lateinit var yValidator: YCoordinateValidator

    @Inject
    private lateinit var rValidator: RadiusValidator

    @Inject
    private lateinit var pointFactory: PointFactory

    @Inject
    private lateinit var resultsRepository: ResultsRepository

    @Throws(ValidationException::class)
    open fun processPoint(requestDto: PointRequestDto): PointEntity {
        xValidator.validate(requestDto.x)
        yValidator.validate(requestDto.y)
        rValidator.validate(requestDto.r)

        val entity = pointFactory.createFromDto(requestDto)
        return resultsRepository.save(entity)
    }
}

