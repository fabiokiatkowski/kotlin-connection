package com.example.oreonconnection

import com.example.oreonconnection.data.users.UserData
import com.example.oreonconnection.model.response.UserInfo
import com.example.oreonconnection.model.response.UserStageInfo
import com.example.oreonconnection.model.response.UserStages
import com.example.oreonconnection.repository.StageRepository
import com.example.oreonconnection.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @Autowired lateinit var jdbcTemplate: JdbcTemplate

    @GetMapping("/users")
    fun getAll(): List<UserData> = UserRepository().findAll(jdbcTemplate)

    @GetMapping("/users/{id}")
    fun getById(@PathVariable id : Int): UserData = UserRepository().findById(jdbcTemplate, id)

    @CrossOrigin(origins = arrayOf("http://localhost:3000"))
    @GetMapping("/users/{id}/stages")
    fun getUserStagesByUserId(@PathVariable id: Int): UserStages {
        val userData = UserRepository().findById(jdbcTemplate, id)
        val userStages = UserRepository().findStagesByUserId(jdbcTemplate, id)
                .map { data -> StageRepository().fingStageById(jdbcTemplate, data.stageId) }
                .map { data -> UserStageInfo(data.stageId, data.stageName, data.stageLeadTime) }
        val user = UserInfo(userData, userStages)
        return UserStages(user)
    }
}