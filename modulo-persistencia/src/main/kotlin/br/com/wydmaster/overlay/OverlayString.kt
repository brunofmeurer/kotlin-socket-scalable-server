package br.com.wydmaster.overlay

/**
 * Criado por bruno-meurer em 27/08/17.
 */
fun String.trimBytes():String{
    var byteArray = toByteArray()
    var retorno = ByteArray(0)


    byteArray.forEachIndexed { index, byte ->
        if(byteArray.get(index).toInt() != 0){
            retorno = retorno.plus(byteArray.get(index))
        }
    }

    return String(retorno)

}