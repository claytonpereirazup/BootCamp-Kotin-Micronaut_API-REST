package br.com.zupacademy.autor.autorbusca

import br.com.zupacademy.autor.AutorRepositoy
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated

@Validated
@Controller(value = "/autores")
class AutorBuscaController(val autorRepositoy: AutorRepositoy) {

    @Get
    fun buscar(): HttpResponse<Any> {
        println("Get Executado!")
        val autoresResponse: List<AutorBuscaResponse> = autorRepositoy.findAll().map { x -> AutorBuscaResponse(x) }

        return HttpResponse.ok(autoresResponse)
    }

}
