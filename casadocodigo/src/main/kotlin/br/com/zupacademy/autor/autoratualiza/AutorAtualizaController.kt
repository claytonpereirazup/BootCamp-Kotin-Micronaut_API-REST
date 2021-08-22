package br.com.zupacademy.autor.autoratualiza

import br.com.zupacademy.autor.AutorRepositoy
import br.com.zupacademy.autor.Autor
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller(value = "/autores/{id}")
class AutorAtualizaController (
    val autorRepositoy: AutorRepositoy
) {

    @Put
    @Transactional
    fun atualiza(@PathVariable id: Long, @Body @Valid request: AutorAtualizaRequest): HttpResponse<Any> {
        val possivelAutor = autorRepositoy.findById(id)
        if (possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }
        var autor: Autor = possivelAutor.get()
        println("Autor ID $id Recuperado: ${autor.toString()}")

        autor = request.toModel(id)
        autorRepositoy.update(autor)

        println("Autor ID $id Atualizado: ${autor.toString()}")
        return HttpResponse.ok(AutorAtualizaResponse(autor))
    }

    @Patch
    @Transactional
    fun atualizaParte(@PathVariable id: Long, descricao: String): HttpResponse<Any> {
        val possivelAutor = autorRepositoy.findById(id)
        if (possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }
        var autor: Autor = possivelAutor.get()
        println("Autor ID $id Recuperado: ${autor.toString()}")

        autor.descricao = descricao
        autorRepositoy.update(autor)

        println("Autor ID $id Atualizado: ${autor.toString()}")
        return HttpResponse.ok(AutorAtualizaResponse(autor))
    }

}