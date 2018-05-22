package br.com.wydmaster.estrutura.pacotes.dependencias

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho

/**
 * Criado por bruno-meurer em 13/10/17.
 */
class Cabecalho : ObjetoPacote {

    constructor(id:Int){
        this.id = id
    }
    @Posicao(1)
    var id:Int = 0

    @Posicao(2)
    @Tamanho(36)
    var token:String = ""
}