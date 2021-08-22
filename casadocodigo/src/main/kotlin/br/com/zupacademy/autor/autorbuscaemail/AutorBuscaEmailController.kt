package br.com.zupacademy.autor.autorbuscaemail

import br.com.zupacademy.autor.AutorRepositoy
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import java.util.*

@Validated
@Controller(value = "/autores")
class AutorBuscaEmailController(val autorRepositoy: AutorRepositoy) {

    @Get(value = "/email") // //autores/email ou /autores/email?email=clayton.pereira@zup.com.br
    fun buscarPorEmail(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        println("GetEmail Executado!")
        if (email.isBlank()){
            val autoresResponse: List<AutorBuscaEmailResponse> = autorRepositoy.findAll().map { x -> AutorBuscaEmailResponse(x) }
            return HttpResponse.ok(autoresResponse)
        }
        val possivalAutor = autorRepositoy.findByEmail(email)
        if (possivalAutor.isEmpty){
            return HttpResponse.notFound()
        }
        val autor = possivalAutor.get()

        return HttpResponse.ok(AutorBuscaEmailResponse(autor))
    }

}
