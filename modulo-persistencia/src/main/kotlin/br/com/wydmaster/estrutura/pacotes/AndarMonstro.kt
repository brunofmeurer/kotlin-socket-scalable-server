package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.comum.Coordenadas
import br.com.wydmaster.estrutura.pacotes.comum.Quaternion
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 28/09/17.
 */
class AndarMonstro {
    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(22)

    @Posicao(3)
    var de = Coordenadas()

    @Posicao(5)
    var ate = Coordenadas()

    @Posicao(8)
    var quaternion: Quaternion = Quaternion()

    @Posicao(9)
    @Tamanho(45)
    var nome:String = ""

    @Posicao(11)
    var numero:Int = 0

}