package br.com.wydmaster.core

import br.com.wydmaster.core.logica.*
import br.com.wydmaster.estrutura.TipoPacoteEnum
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class Interpretador {
    it;

    fun interpretarPacote(pacote:PacoteGenerico, output:PacoteOutputStream){

        when(pacote.cabecalho.id){
            TipoPacoteEnum.LOGIN.id    -> ExecLogin.it.logica(pacote, output)
            TipoPacoteEnum.CHAT.id -> ExecChat.it.logica(pacote, output)
            TipoPacoteEnum.CRIAR_PERSONAGEM.id -> ExecCriaPersonagem.it.logica(pacote, output)
            TipoPacoteEnum.DELETAR_PERSONAGEM.id -> ExecDeletarPersonagem.it.logica(pacote, output)
            TipoPacoteEnum.SELECIONAR_PERSONAGEM.id -> ExecSelecionarPersonagem.it.logica(pacote, output)
            TipoPacoteEnum.DESCONECTA_PERSONAGEM.id -> ExecDesconectarPersonagem.it.logica(pacote, output)
            TipoPacoteEnum.ANDAR.id -> ExecAndar.it.logica(pacote, output)
            TipoPacoteEnum.EQUIPAR_ITEM.id -> ExecEquiparItem.it.logica(pacote, output)
            TipoPacoteEnum.CONSUMIR_ITEM.id -> ExecConsumirItem.it.logica(pacote, output)
            TipoPacoteEnum.DROPAR_ITEM.id -> ExecDroparItem.it.logica(pacote, output)
            TipoPacoteEnum.MOVER_ITEM.id -> ExecMoverItem.it.logica(pacote, output)

            TipoPacoteEnum.ATACAR.id -> ExecAtacar.it.logica(pacote, output)
        }
    }
}