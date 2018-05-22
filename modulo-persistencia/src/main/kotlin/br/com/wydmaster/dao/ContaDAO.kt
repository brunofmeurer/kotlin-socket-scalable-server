package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.ContaDTO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class ContaDAO {

    it;

    val tabela = "conta"

    fun login(conta:ContaDTO):ContaDTO?{

        var retorno:ContaDTO? = null

        val sql = "SELECT id, usuario, senha, ativo, token, email, logado, dinheiro FROM $tabela WHERE usuario = '${conta.usuario}' AND senha = '${conta.senha}' " +
                "AND ativo = true AND logado = false"

        val result = MysqlHelper.it.executarSelect(sql)

        while(result!!.next()){
            val id = result.getInt("id")
            val usuario = result.getString("usuario")
            val senha = result.getString("senha")
            val ativo= result.getBoolean("ativo")
            val token = result.getString("token")
            val email = result.getString("email")
            val logado = result.getBoolean("logado")
            val dinheiro = result.getLong("dinheiro")

            retorno = ContaDTO(id, usuario, senha, ativo, token, email, logado, dinheiro)
        }
        result.close()

        return retorno
    }

    fun buscarTokenConectados():ArrayList<String>{

        val retorno:ArrayList<String> = ArrayList<String>()

        val sql = "SELECT c.token FROM $tabela c, contaPersonagem cp WHERE c.logado = true AND c.id = cp.conta_id AND cp.logado = true"

        val result = MysqlHelper.it.executarSelect(sql)

        while(result!!.next()){

            val token = result.getString("token")

            retorno.add(token)
        }
        result.close()

        return retorno
    }

    fun logado(id:Int, logado:Boolean){
        val sql = "UPDATE $tabela SET logado = $logado WHERE id = $id"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }

    fun salvarToken(id:Int, token:String){
        val sql = "UPDATE $tabela SET token = '$token', logado = true WHERE id = $id"
        //val sql = "UPDATE $tabela SET token = '$token' WHERE id = $id"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }

    fun buscarConta(token:String):ContaDTO?{

        var retorno:ContaDTO? = null

        val sql = "SELECT id, usuario, senha, ativo, token, email, logado, dinheiro FROM $tabela WHERE token = '${token}'"

        val result = MysqlHelper.it.executarSelect(sql)

        while(result!!.next()){
            val id = result.getInt("id")
            val usuario = result.getString("usuario")
            val senha = result.getString("senha")
            val ativo= result.getBoolean("ativo")
            val token = result.getString("token")
            val email = result.getString("email")
            val logado = result.getBoolean("logado")
            val dinheiro = result.getLong("dinheiro")

            retorno = ContaDTO(id, usuario, senha, ativo, token, email, logado, dinheiro)
        }
        result.close()

        return retorno
    }

    fun verificarSenha(token:String, senha:String):Boolean{

        var retorno = false

        val sql = "SELECT count(id) FROM $tabela WHERE token = '${token}' AND senha = '${senha}'"

        val result = MysqlHelper.it.executarSelect(sql)

        while(result!!.next()){
            retorno = true
        }
        result.close()

        return retorno
    }

    fun desconectarTodos(){
        val sql = "UPDATE $tabela SET logado = false WHERE id > 0"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }
}