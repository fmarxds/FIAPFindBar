package br.com.fiap.apifindbar.model

data class EnderecoModel(

    val logradouro: String,
    val numero: String,
    val complemento: String? = null,
    val cep: String,
    val bairro: String,
    val cidade: String,
    val estado: String,

)
