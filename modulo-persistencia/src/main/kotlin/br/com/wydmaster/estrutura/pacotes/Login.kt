package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 26/08/17.
 */
/*Pacote 1*/
class Login  {
    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(1)

    @Posicao(3)
    @Tamanho(20)
    var login:String = ""

    @Posicao(4)
    @Tamanho(20)
    var senha:String = ""
}