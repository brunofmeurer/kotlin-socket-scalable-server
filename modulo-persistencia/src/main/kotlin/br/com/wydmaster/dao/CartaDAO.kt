package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.CartaDTO

/**
 * Criado por bruno-meurer em 24/10/17.
 */
enum class CartaDAO {

    it;

    val tabela = "cartar"

    fun inserir(carta: CartaDTO){
        val sql = "INSERT FROM ${tabela} (mensagem,personagem_id) VALUES('${carta.mensagem}',${carta.personagem_id}"

        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }
}