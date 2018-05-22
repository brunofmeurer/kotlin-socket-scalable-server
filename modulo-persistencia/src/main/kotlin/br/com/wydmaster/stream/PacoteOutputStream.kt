package br.com.wydmaster.stream

import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import java.io.DataOutputStream
import java.io.OutputStream
import java.nio.ByteBuffer

/**
 * Criado por bruno-meurer em 21/08/17.
 */
class PacoteOutputStream: DataOutputStream {
    constructor(output:OutputStream):super(output)

    fun enviarPacote(param:ByteArray){

        var pacoteFechado:ByteArray = ByteArray(0)

        var arraySize = ByteBuffer.allocate(4).putInt(param.size).array()

        arraySize.reverse()

        pacoteFechado = pacoteFechado.plus(arraySize) //size 32bits
        pacoteFechado = pacoteFechado.plus(param)

        var teste = ConverterPacote.it.byteArrayParaObjeto<PacoteGenerico>(param)

        write(pacoteFechado)
        flush()
    }
}