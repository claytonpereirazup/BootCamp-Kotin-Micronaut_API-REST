package br.com.zupacademy.autor.autorcadastro

import br.com.zupacademy.autor.Autor
import br.com.zupacademy.autor.autorcadastro.enderecoclient.Endereco
import br.com.zupacademy.autor.autorcadastro.enderecoclient.EnderecoResponse
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class AutorCadastraRequest(
    @field:NotBlank val nome: String,
    @field:Email @field:NotBlank val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val cep: String
) {
    fun toModel(enderecoResponse: EnderecoResponse): Autor {
        val endereco = Endereco(enderecoResponse)
        return Autor(this.nome, this.email, this.descricao, endereco)
    }
}
