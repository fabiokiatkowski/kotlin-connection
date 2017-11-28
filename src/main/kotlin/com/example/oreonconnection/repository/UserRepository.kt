package com.example.oreonconnection.repository

import com.example.oreonconnection.data.users.UserData
import com.example.oreonconnection.data.users.UserStageData
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    fun findAll(jdbcTemplate: JdbcTemplate) : List<UserData> {
        return jdbcTemplate.query("select cod_usuario, des_usuario, des_apelido from dual")
        {rs, _ ->
            UserData(rs.getInt("cod_usuario"),
                    rs.getString("des_usuario"),
                    rs.getString("des_apelido"))
        }
    }

    fun findById(jdbcTemplate: JdbcTemplate, id: Int): UserData
    {
        return jdbcTemplate.query("select cod_usuario, des_usuario, des_apelido from dual " +
                "where cod_usuario = $id")
        {rs, _ ->
            UserData(rs.getInt("cod_usuario"),
                    rs.getString("des_usuario"),
                    rs.getString("des_apelido"))
        }.single()
    }

    fun findStagesByUserId(jdbcTemplate: JdbcTemplate, id: Int): List<UserStageData>
    {
        return jdbcTemplate.query("select cod_usuario, cod_fase from dual_fase " +
                "where cod_usuario = $id")
        {rs, _  -> UserStageData(rs.getInt("cod_usuario"), rs.getInt("cod_fase")) }
    }
}