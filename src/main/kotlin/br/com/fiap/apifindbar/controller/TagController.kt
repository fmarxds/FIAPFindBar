package br.com.fiap.apifindbar.controller

import br.com.fiap.apifindbar.dto.BarDTO
import br.com.fiap.apifindbar.dto.ListaBarDTO
import br.com.fiap.apifindbar.dto.TagDTO
import br.com.fiap.apifindbar.model.TagModel
import br.com.fiap.apifindbar.service.BarService
import br.com.fiap.apifindbar.service.TagService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/tags")
class TagController(
    private val barService: BarService,
    private val tagService: TagService,
) {

    @GetMapping
    fun findAll(): ResponseEntity<TagDTO> {
    return ResponseEntity.ok(tagService.findAll())
    }


    @PostMapping("/{barId}")
    fun addTags(
        @PathVariable("barId") barId: String,
       @RequestBody tag: TagModel?
    ): ResponseEntity<TagModel?>? {
        return ResponseEntity(tagService.addTag(barId, tag!!), HttpStatus.CREATED)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
   @PatchMapping("/{tagId}")
    fun updateTag(
        @PathVariable("tagId") tagId: String,
        @RequestBody tag: TagDTO
    ):ResponseEntity<TagDTO?>? {
        return ResponseEntity(tagService.updateTag(tagId, tag), HttpStatus.OK)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{tagId}")
    fun deleteTag(
        @PathVariable("tagId") id: String) {
        tagService.deleteTag(id!!)
    }

}