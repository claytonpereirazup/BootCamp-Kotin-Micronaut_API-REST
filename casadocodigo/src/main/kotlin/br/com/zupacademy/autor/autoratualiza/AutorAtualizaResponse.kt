package br.com.zupacademy.autor.autoratualiza

import br.com.zupacademy.autor.Autor

class AutorAtualizaResponse(entity: Autor)  {
    val id: Long? = entity.id
    val nome: String = entity.nome
    val descricao: String = entity.descricao

    override fun toString(): String {
        return "AutorAtualizaResponse(id=$id, nome='$nome', descricao='$descricao')"
    }

}
