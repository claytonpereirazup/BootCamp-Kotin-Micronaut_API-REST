package br.com.zupacademy.autor

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_autor")
data class Autor(
    @Column(nullable = false)
    val nome: String,
    @Column(nullable = false)
    val email: String,
    @Column(nullable = false)
    var descricao: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    val criadoEm: LocalDateTime = LocalDateTime.now()

    constructor(id: Long, nome: String, email: String, descricao: String) : this(
        nome, email, descricao
    ) {
       this.id = id
    }

    override fun toString(): String {
        return "Autor(nome='$nome', email='$email', descricao='$descricao', id=$id)"
    }


}
