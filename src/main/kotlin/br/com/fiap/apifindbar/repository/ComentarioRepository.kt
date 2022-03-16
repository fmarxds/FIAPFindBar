package br.com.fiap.apifindbar.repository

import br.com.fiap.apifindbar.model.ComentarioModel
import org.springframework.data.mongodb.repository.MongoRepository

interface ComentarioRepository: MongoRepository<ComentarioModel, String> {
}