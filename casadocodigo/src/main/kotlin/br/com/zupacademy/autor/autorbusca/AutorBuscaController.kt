package br.com.zupacademy.autor.autorbusca

import br.com.zupacademy.autor.AutorRepositoy
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.transaction.annotation.ReadOnly
import io.micronaut.validation.Validated
import javax.transaction.Transactional

@Validated
@Controller(value = "/autores")
class AutorBuscaController(val autorRepositoy: AutorRepositoy) {

    @Get
    @Transactional
    fun buscar(): HttpResponse<Any> {
        println("Get Executado!")
        val autoresResponse: List<AutorBuscaResponse> = autorRepositoy.findAll().map { x -> AutorBuscaResponse(x) }

        return HttpResponse.ok(autoresResponse)
    }

}
