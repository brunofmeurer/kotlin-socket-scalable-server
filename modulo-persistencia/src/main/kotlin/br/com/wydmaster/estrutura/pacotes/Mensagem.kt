package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 27/08/17.
 */
/*Pacote 6*/
class Mensagem {
    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(6)

    @Posicao(3)
    @Tamanho(200)
    var mensagem:String = ""
}