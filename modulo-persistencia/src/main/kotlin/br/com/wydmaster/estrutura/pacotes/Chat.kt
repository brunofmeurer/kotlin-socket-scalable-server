package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 27/08/17.
 */
/*Pacote 2*/
class Chat {
    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(2)

    @Posicao(3)
    @Tamanho(45)
    var nome:String = ""

    @Posicao(4)
    @Tamanho(200)
    var mensagem:String = ""

    @Posicao(5)
    var todos:Boolean = false
}