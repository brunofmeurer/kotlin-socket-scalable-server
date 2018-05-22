package br.com.wydmaster.service

import br.com.wydmaster.dao.ContaDAO
import br.com.wydmaster.entidade.ContaDTO


/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class ContaService {
    it;

    val dao = ContaDAO.it

    fun login(conta:ContaDTO):ContaDTO?{
        return dao.login(conta)
    }

    fun logado(id:Int, logado:Boolean){
        dao.logado(id, logado)
    }

    fun salvarToken(id:Int, token:String){
        dao.salvarToken(id, token)
    }

    fun buscarTokenConectados():ArrayList<String>{
        return dao.buscarTokenConectados()
    }

    fun buscarConta(token:String):ContaDTO?{
        return dao.buscarConta(token)
    }

    fun verificarSenha(token:String, senha:String):Boolean{
        return dao.verificarSenha(token, senha)
    }

    fun desconectarTodos(){
        dao.desconectarTodos()
        ContaPersonagemService.it.desconectarTodos()
    }
}