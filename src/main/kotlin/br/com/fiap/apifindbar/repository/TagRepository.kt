package br.com.fiap.apifindbar.repository

import br.com.fiap.apifindbar.model.TagModel
import org.springframework.data.mongodb.repository.MongoRepository

interface TagRepository: MongoRepository<TagModel, String> {

    fun findAllByBarId(barId: String): Collection<TagModel>

}