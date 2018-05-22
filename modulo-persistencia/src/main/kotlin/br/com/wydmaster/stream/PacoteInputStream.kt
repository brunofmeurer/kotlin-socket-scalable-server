package br.com.wydmaster.stream

import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import java.io.DataInputStream
import java.io.InputStream
import java.nio.ByteBuffer

/**
 * Criado por bruno-meurer em 21/08/17.
 */
class PacoteInputStream : DataInputStream{

    constructor(input:InputStream) : super(input)

    fun lerPacote():PacoteGenerico{

        //INT32
        var arraySize = ByteArray(4)

        read(arraySize)

        arraySize.reverse()

        val size = ByteBuffer.wrap(arraySize).getInt()

        var pacote = ByteArray(size)
        read(pacote)

        if(size > 0)
            return ConverterPacote.it.byteArrayParaObjeto<PacoteGenerico>(pacote)!!
        else
            return PacoteGenerico()
    }
}