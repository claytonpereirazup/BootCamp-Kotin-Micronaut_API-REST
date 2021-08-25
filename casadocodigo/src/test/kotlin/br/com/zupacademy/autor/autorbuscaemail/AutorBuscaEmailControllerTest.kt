package br.com.zupacademy.autor.autorbuscaemail

import br.com.zupacademy.autor.Autor
import br.com.zupacademy.autor.AutorRepositoy
import br.com.zupacademy.autor.autorbusca.AutorBuscaResponse
import br.com.zupacademy.autor.autorcadastro.AutorCadastraResponse
import br.com.zupacademy.autor.autorcadastro.enderecoclient.Endereco
import br.com.zupacademy.autor.autorcadastro.enderecoclient.EnderecoResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest(  //executa a classe no contexto micronaut
    //Controle Transacional para melhor Isolamento de Teste
    rollback = false, //Commita
    transactionMode = TransactionMode.SINGLE_TRANSACTION, //default: SEPARATE_TRANSACTION (@BeforeEach e @Test mesma transação)
    transactional = false //AutoCommit
)

internal class AutorBuscaEmailControllerTest {

    /**
     * ESTRATEGIAS de ISOLAMENTO
     * Louça: Sujou, Limpou -> @AfterEach
     * Louça: Limpou, Usou -> @BeforeEach [*]
     * Louça: Usa Louça Descatável -> rollback=true
     * Louça: Usou a Louça, Joga Fora, Compra Outra -> recria banco de teste (h2)
     */

    @field:Inject
    lateinit var autorRepositoy: AutorRepositoy

    @field:Inject       //injeta o cliente http e suas requisições para o / (Raiz)
    @field:Client("/")  //da url no contexto do micronaut
    lateinit var client: HttpClient //Bean que esta sendo gerenciado pelo Micronaut e receberá o objeto da requisição

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
        val endereco: Endereco = Endereco(EnderecoResponse("Praça José Pedro de Alcântara", "Casa", "Parque Oratório", "Santo André", "SP"))
        autor = Autor("Clayton Pereira", "clayton.pereira@zup.com.br", "Engenheiro de Software", endereco)
        autorRepositoy.save(autor)
    } //commit

    @AfterEach //limpa o banco a cada transacao
    internal fun tearDown() {
        autorRepositoy.deleteAll()
    } //commit - transação independente

    @Test
    internal fun deveRetornarOsDetalhesDeUmAutor() {

        //executa um Request e armazena a Response na val response
        val response = client.toBlocking().exchange("/autores/email?email=${autor.email}",AutorBuscaEmailResponse::class.java)

        //validações
        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.descricao, response.body()!!.descricao)
        assertEquals(autor.email, response.body()!!.email)

    } // commitada por conta do rollback= false
}