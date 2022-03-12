package br.com.fiap.apifindbar.controller

import br.com.fiap.apifindbar.dto.BarDTO
import br.com.fiap.apifindbar.dto.BarNovoDTO
import br.com.fiap.apifindbar.dto.ListaBarDTO
import br.com.fiap.apifindbar.exception.BarNaoEncontradoException
import br.com.fiap.apifindbar.service.BarService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bares")
class BarController(
    private val barService: BarService,
) {

    @GetMapping
    fun findAll(
        @RequestParam(name = "estilos_musicais", required = false) estilosMusicais: List<String>?,
        @RequestParam(name = "musica_ao_vivo", required = false) musicaAoVivo: Boolean?,
        @RequestParam(name = "avaliacao", required = false) avaliacao: Int?,
    ): ResponseEntity<Collection<BarDTO>> {

        return ResponseEntity.ok(
            barService.findAll(
                ListaBarDTO(
                    estilosMusicais = estilosMusicais,
                    musicaAoVivo = musicaAoVivo,
                    avaliacao = avaliacao
                )
            )
        )

    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable("id") id: String,
    ): ResponseEntity<BarDTO> {

        return try {
            ResponseEntity.ok(barService.findOne(id))
        } catch (ex: BarNaoEncontradoException) {
            ResponseEntity.notFound().build()
        }

    }

    @PostMapping
    fun create(
        @RequestBody bar: BarNovoDTO,
    ): ResponseEntity<BarDTO> {

        return ResponseEntity(barService.createOne(bar), HttpStatus.CREATED)

    }

}