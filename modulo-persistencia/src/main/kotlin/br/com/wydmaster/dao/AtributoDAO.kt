package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.AtributoDTO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class AtributoDAO {

    it;

    val tabela = "atributo"

    fun inserir(atributo:AtributoDTO){
        val sql = "INSERT FROM ${tabela} (id,nome) VALUES('${atributo.id}','${atributo.nome}'"

        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }
}