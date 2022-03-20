package br.com.fiap.apifindbar.service

import br.com.fiap.apifindbar.dto.BarAlteracaoDTO
import br.com.fiap.apifindbar.dto.BarDTO
import br.com.fiap.apifindbar.dto.BarNovoDTO
import br.com.fiap.apifindbar.dto.ListaBarDTO
import org.springframework.http.HttpStatus
import java.util.*
import javax.swing.text.html.parser.Entity

interface BarService {

    fun findAll(filtro: ListaBarDTO): Collection<BarDTO>

    fun findOne(id: String): BarDTO

    fun createOne(novoBarDTO: BarNovoDTO): BarDTO

    fun delete (id: String)

    fun update(id: String, barAlteracaoDTO: BarAlteracaoDTO): BarDTO

}