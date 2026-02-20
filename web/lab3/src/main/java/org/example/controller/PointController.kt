package org.example.controller

import org.example.dto.PointRequestDto
import org.example.entity.PointEntity
import org.example.exception.ExceptionHandler
import org.example.service.PointService
import javax.enterprise.context.RequestScoped
import javax.faces.application.FacesMessage
import javax.faces.context.FacesContext
import javax.inject.Inject
import javax.inject.Named

@Named("pointController")
@RequestScoped
open class PointController {

    @Inject
    private lateinit var pointService: PointService

    @Inject
    private lateinit var pointRequestDto: PointRequestDto

    @Inject
    private lateinit var resultsController: ResultsController

    @Inject
    private lateinit var exceptionHandler: ExceptionHandler

    fun checkPoint(): String? {
        val facesContext = FacesContext.getCurrentInstance()
        
        // Debug сообщение
        facesContext?.addMessage(
            null,
            FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Debug inputs",
                String.format("X=%s, Y=%s, R=%s", pointRequestDto.x, pointRequestDto.y, pointRequestDto.r)
            )
        )

        try {
            val entity = pointService.processPoint(pointRequestDto)
            
            // Обновляем результаты
            resultsController.loadResults()

            // Сообщение об успехе
            facesContext?.addMessage(
                null,
                FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Точка проверена",
                    String.format(
                        "X=%.2f, Y=%.0f, R=%.2f - %s",
                        entity.x ?: 0.0,
                        entity.y ?: 0.0,
                        entity.r ?: 0.0,
                        if (entity.hit == true) "Попадание" else "Мимо"
                    )
                )
            )
        } catch (e: Exception) {
            // Делегируем обработку исключений специализированному классу
            exceptionHandler.handleAndAddToContext(e)
        }

        return null // Остаемся на той же странице
    }
}

