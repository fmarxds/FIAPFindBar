package br.com.fiap.apifindbar.controller

import br.com.fiap.apifindbar.dto.ComentarioDTO
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

    @PostMapping("/bares/{barId}")
    fun addComments(
        @PathVariable("barId") barId: String?,
        @RequestBody comentario: ComentarioDTO?
    ): ResponseEntity<ComentarioDTO?>? {
        return ResponseEntity(comentarioService.addComments(barId!!, comentario!!), HttpStatus.CREATED)
    }

  //  @PutMapping("/{commentId}")
   // fun updateComment(
    //    @PathVariable("commentId") commentId: String?,
    //    @RequestBody comentario: ComentarioDTO
   // ):ResponseEntity<ComentarioDTO?>? {
    //    return ResponseEntity(comentarioService.updateComment(commentId, comentario), HttpStatus.OK)
   // }
}