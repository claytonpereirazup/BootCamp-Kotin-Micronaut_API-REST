package br.com.zupacademy.autor.autorbusca

import br.com.zupacademy.autor.Autor
import br.com.zupacademy.autor.autorcadastro.enderecoclient.Endereco

class AutorBuscaResponse(entity: Autor)  {
    val id: Long? = entity.id
    val nome: String = entity.nome
    val descricao:String = entity.descricao
    val email: String = entity.email
    val endereco: Endereco = entity.endereco
    val criadoEm: String = entity.criadoEm.toString()
}
