package br.com.zupacademy.autor

import br.com.zupacademy.autor.autorcadastro.enderecoclient.Endereco
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
    var descricao: String,
    @Column(nullable = false)
    val endereco: Endereco
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    val criadoEm: LocalDateTime = LocalDateTime.now()


    constructor(id: Long, nome: String, email: String, descricao: String, endereco: Endereco) : this(
        nome, email, descricao, endereco
    ) {
       this.id = id
    }

    override fun toString(): String {
        return "Autor(nome='$nome', email='$email', descricao='$descricao', endereco=$endereco, id=$id)"
    }

}
