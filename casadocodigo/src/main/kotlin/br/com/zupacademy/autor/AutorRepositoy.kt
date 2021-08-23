package br.com.zupacademy.autor

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository("default")
interface AutorRepositoy : JpaRepository<Autor, Long> {

    //Declarative Queries
    fun findByEmail(email: String): Optional<Autor>

    //Create Queries
    @Query(value = "SELECT a FROM Autor a WHERE a.email = :email")
    fun buscaPorEmail(email: String): Optional<Autor>

}