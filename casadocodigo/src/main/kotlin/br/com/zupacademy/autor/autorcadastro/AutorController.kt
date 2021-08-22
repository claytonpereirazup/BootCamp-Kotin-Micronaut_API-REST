package br.com.zupacademy.autor.autorcadastro

import br.com.zupacademy.autor.AutorRepositoy
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller(value = "/autores")
class AutorController(val autorRepositoy: AutorRepositoy) {

    @Post
    @Transactional
    fun cadastrar(@Body @Valid request: AutorRequest): HttpResponse<Any>{
        var novoAutor = request.toModel()
        autorRepositoy.save(novoAutor)
        println("Autor Criado: $novoAutor")

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", novoAutor.id)))

        return HttpResponse.created(uri)
    }

}
