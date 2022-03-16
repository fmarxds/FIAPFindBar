package br.com.fiap.apifindbar.service.impl

import br.com.fiap.apifindbar.converter.BarConverter
import br.com.fiap.apifindbar.dto.BarDTO
import br.com.fiap.apifindbar.dto.BarNovoDTO
import br.com.fiap.apifindbar.dto.ListaBarDTO
import br.com.fiap.apifindbar.exception.BarNaoEncontradoException
import br.com.fiap.apifindbar.model.BarModel
import br.com.fiap.apifindbar.repository.BarRepository
import br.com.fiap.apifindbar.service.BarService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class BarServiceImpl(

        private val barRepository: BarRepository,
        private val barConverter: BarConverter,

        ) : BarService {

    override fun findAll(filtro: ListaBarDTO): Collection<BarDTO> {

        var bares = barRepository.findAll()

        filtro.estilosMusicais?.toSet()?.let { estilos ->
            bares = bares.filter {
                val estilosDoBar = it.estilosMusicais.map { estilo -> estilo.name }
                estilosDoBar.intersect(estilos).isNotEmpty()
            }
        }

        filtro.avaliacao?.let { avaliacao ->
            bares = bares.filter { it.avaliacao.toInt() == avaliacao }
        }

        filtro.musicaAoVivo?.let { aoVivo ->
            bares = bares.filter { it.musicaAoVivo == aoVivo }
        }

        return barConverter.toDTO(bares)

    }

    override fun findOne(id: String): BarDTO {

        return barConverter.toDTO(findOneById(id))

    }

    override fun createOne(novoBarDTO: BarNovoDTO): BarDTO {

        return barConverter.toDTO(barRepository.save(barConverter.toModel(novoBarDTO)))

    }

    override fun delete(id: String) {
        findOneById(id)
        barRepository.deleteById(id)
    }


    private fun findOneById(id: String): BarModel {

        return barRepository.findByIdOrNull(id) ?: throw BarNaoEncontradoException("Bar nao encontrado!")

    }


}

