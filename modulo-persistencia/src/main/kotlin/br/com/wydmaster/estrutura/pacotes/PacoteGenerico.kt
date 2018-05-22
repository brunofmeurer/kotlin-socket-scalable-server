package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 26/08/17.
 */
open class PacoteGenerico {
    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(0)

    @Posicao(3)
    var pacote:ByteArray = ByteArray(0)
}