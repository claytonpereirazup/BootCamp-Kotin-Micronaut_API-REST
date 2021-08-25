package br.com.zupacademy.autor.autorcadastro

import br.com.zupacademy.autor.autorcadastro.enderecoclient.EnderecoClient
import br.com.zupacademy.autor.autorcadastro.enderecoclient.EnderecoResponse
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class AutorCadastraControllerTest {

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject       //injeta o cliente http e suas requisições para o / (Raiz)
    @field:Client("/")  //da url no contexto do micronaut
    lateinit var client: HttpClient //Bean que esta sendo gerenciado pelo Micronaut e receberá o objeto da requisição

    @Test
    internal fun deveCadastrarUmNovoAutor() {

        //cenário
        val autorCadastraRequest = AutorCadastraRequest("Clayton Pereria", "clayton.pereira@zup.com.br", "Engenehiro de Software", "09251-030")
        val request = HttpRequest.POST("/autores", autorCadastraRequest)

        //requisição mockada
        val enderecoResponse = EnderecoResponse("Praça José Pedro de Alcântara", "Casa", "Parque Oratório", "Santo André", "SP")
        Mockito.`when`(enderecoClient.consulta(autorCadastraRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))

        //ação
        val response = client.toBlocking().exchange(request, Any::class.java)

        //testes
        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location").matches("/autores/\\d".toRegex()))
    }

    //Mockando o Bean do Serviço Externo Consulta Cep (roda sem internet)
    @MockBean(EnderecoClient::class)
    fun enderecoMock(): EnderecoClient {
        return Mockito.mock(EnderecoClient::class.java)
    }
}
