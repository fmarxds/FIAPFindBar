package br.com.fiap.apifindbar.service.impl

import br.com.fiap.apifindbar.converter.BarConverter
import br.com.fiap.apifindbar.dto.BarAlteracaoDTO
import br.com.fiap.apifindbar.dto.BarDTO
import br.com.fiap.apifindbar.dto.BarNovoDTO
import br.com.fiap.apifindbar.dto.ListaBarDTO
import br.com.fiap.apifindbar.exception.BarNaoEncontradoException
import br.com.fiap.apifindbar.model.BarModel
import br.com.fiap.apifindbar.model.EnderecoModel
import br.com.fiap.apifindbar.model.EstiloMusicalEnum
import br.com.fiap.apifindbar.model.TipoBarEnum
import br.com.fiap.apifindbar.repository.BarRepository
import br.com.fiap.apifindbar.service.BarService
import br.com.fiap.apifindbar.service.ComentarioService
import br.com.fiap.apifindbar.service.TagService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class BarServiceImpl(

    private val barRepository: BarRepository,
    private val barConverter: BarConverter,
    private val comentarioService: ComentarioService,
    private val tagService: TagService,

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

        bares.forEach {
            it.comentarios = comentarioService.findAllComentarioModelByBarId(it.id!!).toList()
            it.tags = tagService.findAllTagModelByBarId(it.id).toList()
        }

        bares.forEach {
            it.avaliacao = it.comentarios
                ?.map { comentario -> comentario.nota }
                ?.average() ?: 0.0
            if (it.avaliacao.isNaN()) it.avaliacao = 0.0
        }

        return barConverter.toDTO(bares)

    }

    override fun findOne(id: String): BarDTO {

        val bar = findOneById(id)

        bar.comentarios = comentarioService.findAllComentarioModelByBarId(id).toList()
        bar.tags = tagService.findAllTagModelByBarId(id).toList()
        bar.avaliacao = bar.comentarios
            ?.map { comentario -> comentario.nota }
            ?.average() ?: 0.0

        return barConverter.toDTO(bar)

    }

    override fun createOne(novoBarDTO: BarNovoDTO): BarDTO {

        return barConverter.toDTO(barRepository.save(barConverter.toModel(novoBarDTO)))

    }

    override fun delete(id: String) {
        findOneById(id)
        barRepository.deleteById(id)
    }

    override fun update(id: String, barAlteracaoDTO: BarAlteracaoDTO): BarDTO {

        val barModel = findOneById(id)
        barModel.comentarios = comentarioService.findAllComentarioModelByBarId(id).toList()
        barModel.tags = tagService.findAllTagModelByBarId(id).toList()
        barModel.avaliacao = barModel.comentarios
            ?.map { comentario -> comentario.nota }
            ?.average() ?: 0.0

        val barAlterado = BarModel(
            id = barModel.id,
            nome = barAlteracaoDTO.nome ?: barModel.nome,
            horarioAbertura = barAlteracaoDTO.horarioAbertura ?: barModel.horarioAbertura,
            horarioFechamento = barAlteracaoDTO.horarioFechamento ?: barModel.horarioFechamento,
            endereco = EnderecoModel(
                logradouro = barAlteracaoDTO.endereco?.logradouro ?: barModel.endereco.logradouro,
                numero = barAlteracaoDTO.endereco?.numero ?: barModel.endereco.numero,
                complemento = barAlteracaoDTO.endereco?.complemento ?: barModel.endereco.complemento,
                cep = barAlteracaoDTO.endereco?.cep ?: barModel.endereco.cep,
                bairro = barAlteracaoDTO.endereco?.bairro ?: barModel.endereco.bairro,
                cidade = barAlteracaoDTO.endereco?.cidade ?: barModel.endereco.cidade,
                estado = barAlteracaoDTO.endereco?.estado ?: barModel.endereco.estado,
            ),
            tipo = if (barAlteracaoDTO.tipo == null) barModel.tipo else TipoBarEnum.valueOf(barAlteracaoDTO.tipo.name),
            estilosMusicais = if (barAlteracaoDTO.estilosMusicais == null || barAlteracaoDTO.estilosMusicais.isEmpty()) {
                barModel.estilosMusicais
            } else {
                barAlteracaoDTO.estilosMusicais.map {
                    EstiloMusicalEnum.valueOf(it.name)
                }
            },
            musicaAoVivo = barAlteracaoDTO.musicaAoVivo ?: barModel.musicaAoVivo,
            avaliacao = barModel.avaliacao,
            comentarios = barModel.comentarios,
            tags = barModel.tags,
        )

        return barConverter.toDTO(barRepository.save(barAlterado))

    }


    private fun findOneById(id: String): BarModel {

        return barRepository.findByIdOrNull(id) ?: throw BarNaoEncontradoException("Bar nao encontrado!")

    }


}

