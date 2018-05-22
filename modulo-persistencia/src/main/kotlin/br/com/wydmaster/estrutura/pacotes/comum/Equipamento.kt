package br.com.wydmaster.estrutura.pacotes.comum

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.ObjetoPacote

/**
 * Criado por bruno-meurer em 15/10/17.
 */
class Equipamento:ObjetoPacote{

    constructor(){
        for(i in 0 until 3){
            adds.add(AddItem())
        }
    }

    @Posicao(1)
    var idItem:Int = 0

    @Posicao(2)
    var refinacao:Byte = 0

    @Posicao(3)
    @Tamanho(3)
    var adds:ArrayList<AddItem> = ArrayList()

    @Posicao(4)
    var anct:Byte = 0

    @Posicao(5)
    @Tamanho(20)
    var tag:String = ""
}
