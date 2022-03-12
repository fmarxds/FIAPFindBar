package br.com.fiap.apifindbar.service

import br.com.fiap.apifindbar.dto.BarDTO

interface BarService {

    fun findAll(): Collection<BarDTO>

}