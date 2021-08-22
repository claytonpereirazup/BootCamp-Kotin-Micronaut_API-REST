package br.com.zupacademy.autor.autorbusca

import br.com.zupacademy.autor.Autor

class AutorBuscaResponse(entity: Autor)  {
    val id: Long? = entity.id
    val nome: String = entity.nome
    val descricao:String = entity.descricao
    val email: String = entity.email
    val criadoEm: String = entity.criadoEm.toString()
}
