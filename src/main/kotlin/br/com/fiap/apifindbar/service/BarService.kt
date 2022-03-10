package br.com.fiap.apifindbar.service

import br.com.fiap.apifindbar.model.BarModel

interface BarService {

    fun findAll(): List<BarModel>

}