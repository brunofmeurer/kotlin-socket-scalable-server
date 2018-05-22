package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 01/11/17.
 */
class AtacarMonstro {
    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(23)

    @Posicao(2)
    @Tamanho(45)
    var nomeAtacado:String = ""

    @Posicao(3)
    @Tamanho(45)
    var nomeAtacante:String = ""

    @Posicao(4)
    var numero:Int = 0

}