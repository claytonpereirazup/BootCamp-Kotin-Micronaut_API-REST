package br.com.zupacademy.autor

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository("default")
interface AutorRepositoy : JpaRepository<Autor, Long> {

    fun findByEmail(email: String): Optional<Autor>

}