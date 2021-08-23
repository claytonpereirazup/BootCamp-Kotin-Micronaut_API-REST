package br.com.zupacademy.autor.autordeleta

import br.com.zupacademy.autor.AutorRepositoy
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import javax.transaction.Transactional

@Controller(value = "/autores")
class AutorDeleteController(val autorRepositoy: AutorRepositoy){

    @Delete(value = "/{id}")
    @Transactional
    fun deletar(@PathVariable id: Long): HttpResponse<Any> {
        val possivelAutor = autorRepositoy.findById(id)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        autorRepositoy.deleteById(id)

        return HttpResponse.ok()
    }
}