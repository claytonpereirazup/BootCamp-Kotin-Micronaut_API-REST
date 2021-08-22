package br.com.zupacademy.autor.autoratualiza

import br.com.zupacademy.autor.Autor
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class AutorAtualizaRequest(
    @field:NotBlank
    val nome: String,
    @field:Email
    @field:NotBlank
    val email: String,
    @field:NotBlank
    @field:Size(max = 400)
    val descricao: String
) {
    fun toModel(id:Long): Autor {
        return Autor(id, this.nome, this.email, this.descricao)
    }
}
