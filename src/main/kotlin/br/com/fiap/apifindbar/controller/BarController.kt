package br.com.fiap.apifindbar.controller

import br.com.fiap.apifindbar.model.BarModel
import br.com.fiap.apifindbar.service.BarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bares")
class BarController(
    private val barService: BarService,
) {

    @GetMapping
    fun findAll(): List<BarModel> {
        return barService.findAll()
    }

}