package br.com.zupacademy.autor.validadores

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CONSTRUCTOR
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(RUNTIME)
@Target(FIELD, CONSTRUCTOR)
@Constraint(validatedBy = [CepValidator::class])
annotation class Cep(
    val message: String = "Cep com Formato Invalido",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
) {

}

@Singleton
class CepValidator : ConstraintValidator<Cep, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Cep>,
        context: ConstraintValidatorContext
    ): Boolean {

        return  value == null || value.matches("[0-9]{5}-[0-9]{3}".toRegex())
    }

}
