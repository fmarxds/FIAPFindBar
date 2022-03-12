package br.com.fiap.apifindbar.service

import br.com.fiap.apifindbar.dto.BarDTO
import br.com.fiap.apifindbar.dto.BarNovoDTO
import br.com.fiap.apifindbar.dto.ListaBarDTO

interface BarService {

    fun findAll(filtro: ListaBarDTO): Collection<BarDTO>

    fun findOne(id: String): BarDTO

    fun createOne(novoBarDTO: BarNovoDTO): BarDTO

}