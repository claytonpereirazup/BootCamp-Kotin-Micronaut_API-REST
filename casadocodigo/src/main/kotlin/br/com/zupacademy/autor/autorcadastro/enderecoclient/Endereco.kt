package br.com.zupacademy.autor.autorcadastro.enderecoclient

import javax.persistence.Embeddable

@Embeddable
class Endereco(enderecoResponse: EnderecoResponse) {
    val logradouro = enderecoResponse.logradouro
    val complemento = enderecoResponse.complemento
    val bairro = enderecoResponse.bairro
    val localidade = enderecoResponse.localidade
    val uf = enderecoResponse.uf

    override fun toString(): String {
        return "Endereco(logradouro='$logradouro', complemento='$complemento', bairro='$bairro', localidade='$localidade', uf='$uf')"
    }
}
