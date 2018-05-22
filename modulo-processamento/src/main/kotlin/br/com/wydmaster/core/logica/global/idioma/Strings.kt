package br.com.wydmaster.core.logica.global.idioma

/**
 * Criado por bruno-meurer em 16/10/17.
 */
enum class Strings {
    it;

    var idioma = Idioma.PT

    private var stringBR = ArrayList<Mensagem>()
    private var stringUS = ArrayList<Mensagem>()
    private var stringES = ArrayList<Mensagem>()

    constructor(){
        addTexto(
                "PERSONAGEM_INVALIDO",
                "Deu merda. Personagem que tu mandou nao ta marcado como logado no banco de dados.",
                "Holy Shit. Character that you select is not check with loggin in to data base",
                "Ai Caramba! El personaje que has mandado no está marcado como registrado en la base de datos"
        )

        addTexto(
                "IEM_NÃO_PERMITIDO_PARA_CLASSE",
                "Item não permitido para sua classe.",
                "Item not allowed for your class",
                "Elemento no permitido para su clase"
        )

        addTexto(
                "NIVEL_INSUFICIENTE_PARA_ITEM",
                "Nivel insuficiente para utilizar este item",
                "Insufficient level to use this item",
                "Nivel insuficiente para utilizar este elemento"
        )

        addTexto(
                "PONTOS_EM_FORCA_INSUFICIENTES",
                "Pontos em força insuficientes para utilizar este item.",
                "Insufficient strength points to use this item.",
                "Puntos en fuerza insuficientes para utilizar este elemento."
        )

        addTexto(
                "PONTOS_EM_INT_INSUFICIENTES",
                "Pontos em inteligencia insuficientes para utilizar este item.",
                "Insufficient inteligence points to use this item.",
                "Puntos en inteligencia insuficientes para utilizar este elemento."
        )

        addTexto(
                "PONTOS_EM_DES_INSUFICIENTES",
                "Pontos em destreza insuficientes para utilizar este item.",
                "Insufficient dexterity points to use this item.",
                "Puntos en destreza insuficientes para utilizar este elemento."
        )

        addTexto(
                "PONTOS_EM_CONS_INSUFICIENTES",
                "Pontos em constituição insuficientes para utilizar este item.",
                "Insufficient constitution points to use this item.",
                "Puntos en constitución insuficientes para utilizar este elemento."
        )

        addTexto(
                "SLOT_INVALIDO",
                "Item não pode ser equipado neste slot",
                "Item can not be equipped in this slot.",
                "El elemento no se puede equipar en esta ranura."
        )

        addTexto(
                "NOME_JA_UTILIZADO",
                "Nome já utilizado",
                "Name already used",
                "Nombre ya utilizado"
        )

        addTexto(
                "SENHA_INVALIDA",
                "Senha Inválida",
                "Invalida Password",
                "Senha invalida"
        )

        addTexto(
                "NIVEL_IMPEDE_DELETAR_PERSONAGEM",
                "Personagem com nivel maior que 100 só pode ser deletado através de macumba",
                "Character with level greater than 100 can only be deleted via macumba",
                "Personaje con nivel mayor que 100 sólo puede ser borrado a través de macumba"
        )

        addTexto(
                "PERSONAGEM_DELETADO_COM_SUCESSO",
                "Personagem deletado com sucesso!",
                "Deleted character successfully",
                "Personaje eliminado con éxito"
        )
    }

    private fun addTexto(id:String, BR:String, US:String, ES:String){

        stringBR.add(Mensagem(id,BR))
        stringUS.add(Mensagem(id,US))
        stringES.add(Mensagem(id,ES))
    }

    fun get(id:String):String{
        when(idioma){
            Idioma.PT ->{return stringBR.find{it.id.equals(id)}!!.mensagem}
            Idioma.US ->{return stringUS.find{it.id.equals(id)}!!.mensagem}
            Idioma.ES ->{return stringES.find{it.id.equals(id)}!!.mensagem}
        }
    }
}