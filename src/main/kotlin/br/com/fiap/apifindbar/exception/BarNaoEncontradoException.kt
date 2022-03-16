package br.com.fiap.apifindbar.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class BarNaoEncontradoException(mensagem: String, throwable: Throwable? = null): RuntimeException(mensagem, throwable)