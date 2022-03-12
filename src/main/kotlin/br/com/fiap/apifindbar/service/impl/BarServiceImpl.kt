package br.com.fiap.apifindbar.service.impl

import br.com.fiap.apifindbar.converter.BarConverter
import br.com.fiap.apifindbar.dto.BarDTO
import br.com.fiap.apifindbar.repository.BarRepository
import br.com.fiap.apifindbar.service.BarService
import org.springframework.stereotype.Service

@Service
class BarServiceImpl(

    private val barRepository: BarRepository,
    private val barConverter: BarConverter,

) : BarService {

    override fun findAll(): Collection<BarDTO> {
        return barConverter.toDTO(barRepository.findAll())

    }

}