package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.ColisaoDTO

/**
 * Criado por bruno-meurer em 23/10/17.
 */
enum class GerarItemDAO {

    it;

    val tabela = "gerarItem"

    fun inserir(colisaoDTO: ColisaoDTO): ColisaoDTO {
        val sql = "INSERT INTO ${tabela} () VALUES()"

        var result = MysqlHelper.it.executarInsertUpdateDelete(sql)

        colisaoDTO.id = result!!

        return colisaoDTO
    }
}