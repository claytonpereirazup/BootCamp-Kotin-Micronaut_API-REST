package br.com.zupacademy.autor.autorcadastro.enderecoclient

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client(value = "https://viacep.com.br/ws/")
interface EnderecoClient {

    //https://viacep.com.br/ws/{cep}/json
    //https://viacep.com.br/ws/{cep}/xml
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    @Get(value = "{cep}/json", consumes = [MediaType.APPLICATION_XML])
    fun consulta(@QueryValue cep: String): HttpResponse<EnderecoResponse>
}