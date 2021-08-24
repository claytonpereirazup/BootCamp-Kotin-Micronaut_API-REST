package br.com.zupacademy.autor.autorcadastro

import br.com.zupacademy.autor.AutorRepositoy
import br.com.zupacademy.autor.autorcadastro.enderecoclient.EnderecoClient
import br.com.zupacademy.autor.autorcadastro.enderecoclient.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller(value = "/autores")
class AutorCadastraController(val autorRepositoy: AutorRepositoy,
                              val enderecoClient: EnderecoClient
) {

    @Post
    @Transactional
    fun cadastrar(@Body @Valid request: AutorCadastraRequest): HttpResponse<Any>{
        //Cliente HTTP - Faz uma requisição a um serviço externo

        val enderecoResponse:HttpResponse<EnderecoResponse> = enderecoClient.consulta(request.cep)

        var novoAutor = request.toModel(enderecoResponse.body()!!)
        autorRepositoy.save(novoAutor)
        println("Autor Criado: $novoAutor")

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", novoAutor.id)))

        return HttpResponse.created(uri)
    }

}
