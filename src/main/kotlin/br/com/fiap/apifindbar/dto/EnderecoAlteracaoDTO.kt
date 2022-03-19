package br.com.fiap.apifindbar.dto

data class EnderecoAlteracaoDTO(

    val logradouro: String? = null,
    val numero: String? = null,
    val complemento: String? = null,
    val cep: String? = null,
    val bairro: String? = null,
    val cidade: String? = null,
    val estado: String? = null,

)
