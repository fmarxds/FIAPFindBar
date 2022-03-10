package br.com.fiap.apifindbar.service.impl

import br.com.fiap.apifindbar.model.BarModel
import br.com.fiap.apifindbar.repository.BarRepository
import br.com.fiap.apifindbar.service.BarService
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
class BarServiceImpl(

    private val barRepository: BarRepository,

) : BarService {

    override fun findAll(): List<BarModel> {
        return barRepository.findAll()
    }

}