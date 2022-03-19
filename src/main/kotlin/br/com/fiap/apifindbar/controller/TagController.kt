package br.com.fiap.apifindbar.controller

import br.com.fiap.apifindbar.dto.TagDTO
import br.com.fiap.apifindbar.service.BarService
import br.com.fiap.apifindbar.service.TagService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tags")
class TagController(
    private val tagService: TagService
) {
    @PostMapping("/bares/{barId}")
    fun addTag(
        @PathVariable("barId") barId: String?,
        @RequestBody tag: TagDTO?
    ): ResponseEntity<TagDTO>?{
        return ResponseEntity(tagService.addTag(barId!!, tag!!), HttpStatus.CREATED)
    }
}