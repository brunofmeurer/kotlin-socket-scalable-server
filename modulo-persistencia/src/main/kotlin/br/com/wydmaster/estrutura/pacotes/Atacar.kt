package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.comum.Coordenadas
import br.com.wydmaster.estrutura.pacotes.comum.Quaternion
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 01/11/17.
 */
class Atacar {
    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(17)

    @Posicao(2)
    @Tamanho(45)
    var nome:String = ""

}