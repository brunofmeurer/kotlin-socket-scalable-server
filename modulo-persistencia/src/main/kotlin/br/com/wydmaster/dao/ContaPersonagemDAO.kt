package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.ContaPersonagemDTO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class ContaPersonagemDAO {

    it;

    val tabela = "contaPersonagem"

    fun inserir(contaPersonagem:ContaPersonagemDTO):ContaPersonagemDTO{
        val sql =
                "INSERT INTO ${tabela}\n" +
                "(personagem_id, logado, conta_id, anuncio)\n" +
                "VALUES(${contaPersonagem.persoagemId}," +
                        "${contaPersonagem.logado}," +
                        "${contaPersonagem.contaId}," +
                        "'${contaPersonagem.anuncio}');"

        val result = MysqlHelper.it.executarInsertUpdateDelete(sql)

        contaPersonagem.id = result!!
        return contaPersonagem
    }

    fun buscaQuantidade(token:String):Int{
        val sql =
                "SELECT COUNT(cp.id) FROM $tabela cp INNER JOIN conta c ON cp.conta_id = c.id " +
                "WHERE c.token = '$token'"

        val result = MysqlHelper.it.executarSelect(sql)

        var retorno = 0
        while(result!!.next()){
            retorno= result.getInt(1)
        }
        result.close()

        return retorno
    }

    fun logado(personagemId:Int){
        val sql = "UPDATE $tabela SET logado = true WHERE personagem_id = ${personagemId}"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }

    fun deslogado(token:String){
        val sql = "UPDATE $tabela cp, " +
                "(SELECT cp.id FROM contaPersonagem cp, conta c WHERE c.token = '${token}' AND cp.conta_id = c.id AND cp.logado = true) cp2 SET cp.logado = false WHERE cp.id = cp2.id"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }

    fun deletar(personagemId:Int){
        val sql = "DELETE FROM $tabela WHERE personagem_id = $personagemId"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }

    fun desconectarTodos(){
        val sql = "UPDATE $tabela SET logado = false WHERE id > 0"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }
}