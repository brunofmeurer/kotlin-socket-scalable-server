package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 28/09/17.
 */
class SelecionarPersonagem {

    @Posicao(1)
    var cabecalho:Cabecalho = Cabecalho(8)

    @Posicao(2)
    @Tamanho(45)
    var nome:String = ""
}

