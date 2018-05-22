package br.com.wydmaster.estrutura

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.ObjetoPacote
import br.com.wydmaster.overlay.trimBytes
import java.nio.ByteBuffer
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.*
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.jvmErasure

/**
 * Criado por bruno-meurer em 24/08/17.
 */
enum class ConverterPacote {
    it;

    inline fun < reified T : Any> byteArrayParaObjeto(array:ByteArray):T {

        var instance = T::class.createInstance()

        return byteArrayParaObjeto<T>(array, instance)

    }

    var numeroDeBytes = 0;

    fun < T : Any> byteArrayParaObjeto(array:ByteArray, retorno:T):T{


        var propriedades = retorno::class.declaredMemberProperties.filterIsInstance<KMutableProperty<*>>()

        propriedades = propriedades.sortedBy {
            var posicao= it.findAnnotation<Posicao>()
            if(posicao != null)
                posicao!!.pos
            else
                0
        }

        var posicaoLeitura = 0

        propriedades.forEach {

            when(it.returnType.javaType){

                Double::class.java, Double::class.javaPrimitiveType->{ //8

                    var parte = desmembraArray(array, posicaoLeitura, getPosicaoFim(posicaoLeitura, 8))
                    parte.reverse()

                    var double = ByteBuffer.wrap(parte).getDouble()

                    it.setter.call(retorno, double)

                    posicaoLeitura += 8

                }
                Float::class.java, Float::class.javaPrimitiveType->{ //4


                    var parte = desmembraArray(array, posicaoLeitura, getPosicaoFim(posicaoLeitura, 4))
                    parte.reverse()

                    var float = ByteBuffer.wrap(parte).getFloat()

                    it.setter.call(retorno, float )


                    posicaoLeitura += 4

                }
                Long::class.java, Long::class.javaPrimitiveType->{ //8


                    var parte = desmembraArray(array, posicaoLeitura, getPosicaoFim(posicaoLeitura, 8))
                    parte.reverse()

                    var long = ByteBuffer.wrap(parte).getLong()

                    it.setter.call(retorno, long)


                    posicaoLeitura += 8

                }
                Int::class.java, Int::class.javaPrimitiveType->{ //4


                    var parte = desmembraArray(array,  posicaoLeitura, getPosicaoFim(posicaoLeitura, 4))
                    parte.reverse()

                    var int = ByteBuffer.wrap(parte).getInt()

                    it.setter.call(retorno, int)

                    posicaoLeitura += 4

                }
                Short::class.java, Short::class.javaPrimitiveType->{ //2


                    var parte = desmembraArray(array, posicaoLeitura, getPosicaoFim(posicaoLeitura, 2))
                    parte.reverse()

                    var short = ByteBuffer.wrap(parte).getShort()

                    it.setter.call(retorno, short)

                    posicaoLeitura += 2

                }

                String::class.java->{//indefinido
                    var tamanho = it.findAnnotation<Tamanho>()

                    if(tamanho != null){
                        var parte = desmembraArray(array, posicaoLeitura, posicaoLeitura + tamanho.valor - 1)

                        var string= String(parte)

                        it.setter.call(retorno, string.trimBytes())

                        posicaoLeitura += tamanho.valor
                    }
                }
                Byte::class.java, Byte::class.javaPrimitiveType->{//1

                    var byte = array.get(posicaoLeitura)
                    it.setter.call(retorno, byte)

                    posicaoLeitura += 1


                }

                Boolean::class.java, Boolean::class.javaPrimitiveType->{//1

                    var byte = array.get(posicaoLeitura)

                    it.setter.call(retorno, byte.toInt() == 1)

                    posicaoLeitura += 1

                }
                //tratativa pacote generico
                ByteArray::class.java->{
                    it.setter.call(retorno, array)
                }


                else ->{

                    //trata ArrayList
                    if(it.returnType.javaType.typeName.contains("ArrayList")){

                        var tamanho= it.findAnnotation<Tamanho>()


                        val arrayListZero = it.getter.call(retorno) as ArrayList<Any>
                        var arrayListNovo = ArrayList<Any>()

                        if(tamanho != null && tamanho.valor > 0) {

                            arrayListZero.forEach {
                                arrayListNovo.add(byteArrayParaObjeto(desmembraArray(array, posicaoLeitura, array.size - 1), it))
                                posicaoLeitura += numeroDeBytes
                            }

                            it.setter.call(retorno, arrayListNovo)
                        }else{
                            System.out.println("ArrayList não convertido, pois o numero de posições difere do size do array. Size: ${array.size}")
                        }

                    }else {

                        var classe = it.returnType.jvmErasure.superclasses.find { it.simpleName == "ObjetoPacote" }

                        if(classe != null) {
                            var valor:Any = byteArrayParaObjeto(desmembraArray(array, posicaoLeitura, array.size - 1), it.getter.call(retorno)!!)

                            it.setter.call(retorno, valor)
                            posicaoLeitura += numeroDeBytes
                        }else{
                            System.out.println("Tipo de dados não tradado:" + it.returnType.javaType);
                        }
                    }
                }
            }

            numeroDeBytes = posicaoLeitura;
        }

        return retorno
    }

    fun < T : Any> objetoParaByteArray(obj:T):ByteArray{

        var retorno = ByteArray(0)

        var propriedades = obj::class.declaredMemberProperties.filterIsInstance<KMutableProperty<*>>()

        propriedades = propriedades.sortedBy {
            var posicao= it.findAnnotation<Posicao>()
            if(posicao != null)
                posicao!!.pos
            else
                0
        }

        propriedades.forEach {

            when(it.returnType.javaType){
                Byte::class.java, Byte::class.javaPrimitiveType->{

                    var value = it.getter.call(obj) as Byte
                    retorno = retorno.plus(value)


                }
                Short::class.java, Short::class.javaPrimitiveType->{

                    var value = it.getter.call(obj) as Short
                    var shortByteArray = ByteBuffer.allocate(2 /* 16  bits*/).putShort(value).array()
                    shortByteArray.reverse()
                    retorno = retorno.plus(shortByteArray)


                }
                Int::class.java, Int::class.javaPrimitiveType->{


                    var value = it.getter.call(obj) as Int
                    var intByteArray = ByteBuffer.allocate(4 /* 32 bits*/).putInt(value).array()
                    intByteArray.reverse()
                    retorno = retorno.plus(intByteArray)


                }
                Long::class.java, Long::class.javaPrimitiveType->{

                    var value = it.getter.call(obj) as Long
                    var longByteArray = ByteBuffer.allocate(8 /* 64  bits*/).putLong(value).array()
                    longByteArray.reverse()
                    retorno = retorno.plus(longByteArray)


                }
                Float::class.java, Float::class.javaPrimitiveType->{

                    var value = it.getter.call(obj) as Float
                    var floatByteArray = ByteBuffer.allocate(4 /* 32  bits*/).putFloat(value).array()
                    floatByteArray.reverse()
                    retorno = retorno.plus(floatByteArray)


                }
                Double::class.java, Double::class.javaPrimitiveType->{

                    var value = it.getter.call(obj) as Double
                    var doubleByteArray = ByteBuffer.allocate(8 /* 64  bits*/).putDouble(value).array()
                    doubleByteArray.reverse()
                    retorno = retorno.plus(doubleByteArray)

                }
                String::class.java->{
                    var tamanho= it.findAnnotation<Tamanho>()

                    if(tamanho != null && tamanho.valor > 0){

                        var value = it.getter.call(obj) as String
                        var stringByteArray = ByteArray(tamanho.valor)

                        value.toByteArray().forEachIndexed { index, byte ->
                            stringByteArray[index] = value.toByteArray()[index]
                        }

                        retorno = retorno.plus(stringByteArray)
                    }
                }

                Boolean::class.java, Boolean::class.javaPrimitiveType->{//1

                    var value = it.getter.call(obj) as Boolean

                    if(value)
                        retorno = retorno.plus(1)
                    else
                        retorno = retorno.plus(0)
                }

                else ->{

                    //trata ArrayList
                    if(it.returnType.javaType.typeName.contains("ArrayList")){

                        var tamanho= it.findAnnotation<Tamanho>()
                        var array = it.getter.call(obj) as ArrayList<Any>

                        if(tamanho != null && tamanho.valor > 0 && array.size == tamanho.valor) {
                            array.forEach {
                                retorno = retorno.plus(objetoParaByteArray(it))
                            }
                        }else{
                            System.out.println("ArrayList não convertido, pois o numero de posições difere do size do array. Size: ${array.size}")
                        }

                    }else {
                        var classe = it.returnType.jvmErasure.superclasses.find { it.simpleName == "ObjetoPacote" }

                        if(classe != null) {

                            retorno = retorno.plus(objetoParaByteArray(it.getter.call(obj)!!))

                        }else{
                            System.out.println("Tipo de dados não tradado:" + it.returnType.javaType);
                        }
                    }
                }
            }
        }

        return retorno
    }

    fun desmembraArray(array:ByteArray, ini:Int, fim:Int):ByteArray{
        var retorno = ByteArray(0)

        var cont = 0;
        for (byte in array) {
            if(cont >= ini && cont <= fim){
                retorno = retorno.plus(byte)
            }
            cont++
        }

        return retorno

    }

    fun getPosicaoFim(posicaoLeitura:Int, numero:Int):Int{
        return posicaoLeitura + numero - 1;
    }

    /*Criptografia de teste*/
    fun criptografia(pacote:ByteArray):ByteArray{

        var retorno = ByteArray(0)

        pacote.reverse()

        return retorno
    }

    /*descriptografia de teste*/
    fun descriptografia(pacote:ByteArray):ByteArray{

        var retorno = ByteArray(0)
        pacote.reverse()

        return retorno
    }
}