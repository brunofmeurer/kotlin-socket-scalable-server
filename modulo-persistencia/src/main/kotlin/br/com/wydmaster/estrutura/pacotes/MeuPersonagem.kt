package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.entidade.PersonagemDTO
import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.comum.Equipamento
import br.com.wydmaster.estrutura.pacotes.comum.Inventario
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 28/09/17.
 */
class MeuPersonagem {

    constructor(){
        for (i in 0 until 15) {
            equipamentos.add(Equipamento())
        }

        for (i in 0 until 60) {
            inventario.add(Inventario())
        }
    }

    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(9)

    @Posicao(4)
    @Tamanho(15)
    var equipamentos:ArrayList<Equipamento> = ArrayList()

    @Posicao(5)
    @Tamanho(60)
    var inventario:ArrayList<Inventario> = ArrayList()

    @Posicao(7)
    var personagemDTO:PersonagemDTO = PersonagemDTO()
}

