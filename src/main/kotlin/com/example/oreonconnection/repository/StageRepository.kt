package com.example.oreonconnection.repository

import com.example.oreonconnection.data.stage.StageData
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class StageRepository {
    fun fingStageById(jdbcTemplate: JdbcTemplate, id: Int): StageData {
        return jdbcTemplate.query("select codigo_estagio, descricao, leed_time from dual " +
                "where codigo_estagio = $id")
        {rs, _ ->
            StageData(rs.getInt("codigo_estagio"),
                    rs.getString("descricao"),
                    rs.getDouble("leed_time"))}.single()
    }
}