package br.com.fiap.apifindbar.repository

import br.com.fiap.apifindbar.model.BarModel
import org.springframework.data.mongodb.repository.MongoRepository

interface BarRepository: MongoRepository<BarModel, String>