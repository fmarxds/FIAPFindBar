package br.com.fiap.apifindbar.controller

import br.com.fiap.apifindbar.dto.ComentarioDTO
import br.com.fiap.apifindbar.model.ComentarioModel
import br.com.fiap.apifindbar.service.BarService
import br.com.fiap.apifindbar.service.ComentarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/comentarios")
class ComentarioController(
        private val barService: BarService,
        private val comentarioService: ComentarioService,
) {

    @PostMapping
    fun addComments(
       @RequestBody comentario: ComentarioModel?
    ): ResponseEntity<ComentarioModel?>? {
        return ResponseEntity(comentarioService.addComments(comentario!!), HttpStatus.CREATED)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
   @PatchMapping("/{commentId}")
    fun updateComment(
        @PathVariable("commentId") commentId: String,
        @RequestBody comentario: ComentarioDTO
    ):ResponseEntity<ComentarioDTO?>? {
        return ResponseEntity(comentarioService.updateComment(commentId, comentario), HttpStatus.OK)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable("commentId") id: String) {
        comentarioService.deleteComment(id!!)
    }

 /*   @GetMapping("/{id}")
    fun findById(
        @PathVariable("id") id: String
    ): ResponseEntity<ComentarioDTO> {

        return try {
            ResponseEntity.ok(comentarioService.findOne(id))
        } catch (ex: ComentarioNaoEncontradoException) {
            ResponseEntity.notFound().build()
        }

    }*/
}