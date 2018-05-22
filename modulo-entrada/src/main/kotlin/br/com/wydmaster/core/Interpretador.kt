package br.com.wydmaster.core

import br.com.wydmaster.entidade.Acoplhado
import br.com.wydmaster.entidade.ConexaoClient
import br.com.wydmaster.entidade.ContaDTO
import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.DesconectarPersonagem
import br.com.wydmaster.estrutura.pacotes.Login
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.service.ContaService
import java.util.*
import kotlin.collections.ArrayList

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class Interpretador {
    it;

    var listaConexoes: ArrayList<ConexaoClient> = ArrayList()
    var listaAcoplados: ArrayList<Acoplhado> = ArrayList()

    var cont=0
    fun primeiroRequest(conexao:ConexaoClient, pacote:PacoteGenerico):ContaDTO?{

        var pacoteLogin = ConverterPacote.it.byteArrayParaObjeto<Login>(pacote.pacote)
        var conta:ContaDTO? = ContaDTO(0,pacoteLogin!!.login, pacoteLogin!!.senha, false, null, "", false, 0)

        conta = ContaService.it.login(conta!!)

        if(conta != null && conta.ativo){

            conexao.conta = conta;
            listaConexoes.add(conexao)
        }else{
            return null
        }

        var token = (conta.id.toString() + UUID.randomUUID().toString()).substring(0,35)

        ContaService.it.salvarToken(conta.id, token)

        pacoteLogin.cabecalho.token = token
        conta.token = token

        var bytesPacoteEnvio = ConverterPacote.it.objetoParaByteArray<Login>(pacoteLogin)
        var pacoteEnvio = ConverterPacote.it.byteArrayParaObjeto<PacoteGenerico>(bytesPacoteEnvio)

        trataPacote(pacoteEnvio!!)

        return conta
    }

    fun trataPacote(pacote:PacoteGenerico){

        this.listaAcoplados.get(cont).output.enviarPacote(pacote.pacote)

        if(cont< (this.listaAcoplados.size - 1))
            cont++
        else
            cont = 0
    }

    fun logout(conta:ContaDTO?){
        if(conta != null) {
            ContaService.it.logado(conta.id, false)

            if (conta.token != null) {

                var desconecta = DesconectarPersonagem()

                desconecta.cabecalho.token = conta.token!!

                this.listaAcoplados.get(cont).output.enviarPacote(ConverterPacote.it.objetoParaByteArray(desconecta))

                var listaTemporaria = ArrayList<ConexaoClient>(listaConexoes)
                listaTemporaria.forEach({
                    if (it.conta!!.token.equals(conta.token)) {
                        listaConexoes.remove(it)
                    }
                })
            }
        }
    }
}