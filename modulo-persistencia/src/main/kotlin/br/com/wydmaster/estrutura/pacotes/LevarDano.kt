package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 01/11/17.
 */
class LevarDano {
    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(19)

    @Posicao(2)
    @Tamanho(45)
    var nome:String = ""

    @Posicao(3)
    var dano:Short = 0

}