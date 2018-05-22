package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.entidade.PersonagemDTO
import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.comum.Equipamento
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 28/09/17.
 */
class OutroPersonagem {

    constructor(){
        for (i in 0 until 15) {
            equipamentos.add(Equipamento())
        }
    }

    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(11)

    @Posicao(4)
    @Tamanho(15)
    var equipamentos:ArrayList<Equipamento> = ArrayList()

    @Posicao(7)
    var personagemDTO: PersonagemDTO = PersonagemDTO()
}
