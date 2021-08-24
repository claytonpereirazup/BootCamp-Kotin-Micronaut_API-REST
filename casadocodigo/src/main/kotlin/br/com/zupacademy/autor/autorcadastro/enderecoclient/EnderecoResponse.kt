package br.com.zupacademy.autor.autorcadastro.enderecoclient

data class EnderecoResponse(
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
) {

}
