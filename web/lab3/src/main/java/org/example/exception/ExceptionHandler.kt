package org.example.exception

import org.example.validation.ValidationException
import javax.enterprise.context.ApplicationScoped
import javax.faces.application.FacesMessage
import javax.faces.context.FacesContext

@ApplicationScoped
open class ExceptionHandler {

    open fun handleValidationException(e: ValidationException): FacesMessage {
        return FacesMessage(
            FacesMessage.SEVERITY_ERROR,
            "Ошибка валидации",
            e.message
        )
    }

    open fun handleRepositoryException(e: RepositoryException): FacesMessage {
        return FacesMessage(
            FacesMessage.SEVERITY_ERROR,
            "Ошибка базы данных",
            e.message ?: "Произошла ошибка при работе с базой данных"
        )
    }

    open fun handleException(e: Exception): FacesMessage {
        return when (e) {
            is ValidationException -> handleValidationException(e)
            is RepositoryException -> handleRepositoryException(e)
            else -> FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "Ошибка",
                "Произошла ошибка при обработке запроса: ${e.message}"
            )
        }
    }

    open fun addMessageToContext(message: FacesMessage) {
        val facesContext = FacesContext.getCurrentInstance()
        facesContext?.addMessage(null, message)
    }

    open fun handleAndAddToContext(e: Exception) {
        val message = handleException(e)
        addMessageToContext(message)
    }
}

