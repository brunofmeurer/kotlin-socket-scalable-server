package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 27/08/17.
 */
/*Pacote 4*/
class CriaPersonagem {
    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(4)

    @Posicao(3)
    @Tamanho(45)
    var nome:String = ""

    @Posicao(4)
    var tipo:Byte = 0 /*0 ht, 1 bm, 2 fm e 3 tk*/
}