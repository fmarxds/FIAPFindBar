package br.com.fiap.apifindbar.controller

import br.com.fiap.apifindbar.dto.TagAlteracaoDTO
import br.com.fiap.apifindbar.dto.TagDTO
import br.com.fiap.apifindbar.dto.TagNovoDTO
import br.com.fiap.apifindbar.service.TagService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/tags")
class TagController(
    private val tagService: TagService,
) {

    @GetMapping
    fun findAll(): ResponseEntity<Collection<TagDTO>> {
        return ResponseEntity.ok(tagService.findAll())
    }

    @PostMapping("/bares/{barId}")
    fun addTags(
        @PathVariable("barId") barId: String,
        @RequestBody tag: TagNovoDTO
    ): ResponseEntity<TagDTO> {
        return ResponseEntity(tagService.addTag(barId, tag), HttpStatus.CREATED)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{tagId}")
    fun updateTag(
        @PathVariable("tagId") tagId: String,
        @RequestBody tag: TagAlteracaoDTO
    ): ResponseEntity<TagDTO> {
        return ResponseEntity(tagService.updateTag(tagId, tag), HttpStatus.OK)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{tagId}")
    fun deleteTag(
        @PathVariable("tagId") id: String
    ) {
        tagService.deleteTag(id)
    }

}