package org.example.service

import org.example.dto.PointRequestDto
import org.example.entity.PointEntity
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
open class PointFactory {

    @Inject
    private lateinit var areaChecker: AreaChecker

    open fun createFromDto(requestDto: PointRequestDto): PointEntity {
        val hit = areaChecker.checkHit(
            requestDto.x ?: 0.0,
            requestDto.y ?: 0.0,
            requestDto.r ?: 0.0
        )

        return PointEntity(
            requestDto.x,
            requestDto.y,
            requestDto.r,
            hit
        )
    }
}

