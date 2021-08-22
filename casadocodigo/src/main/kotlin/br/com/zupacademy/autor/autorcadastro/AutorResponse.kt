package br.com.zupacademy.autor.autorcadastro

import br.com.zupacademy.autor.Autor

class AutorResponse(entity: Autor)  {
    val id: Long? = entity.id
    val nome: String = entity.nome
    val criadoEm: String = entity.criadoEm.toString()
}
