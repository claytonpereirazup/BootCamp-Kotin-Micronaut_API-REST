package br.com.zupacademy.autor.autorcadastro.enderecoclient

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client(value = "https://viacep.com.br/ws/")
interface EnderecoClient {

    //https://viacep.com.br/ws/{cep}/json
    @Get(value = "{cep}/json")
    fun consulta(cep: String): HttpResponse<EnderecoResponse>
}