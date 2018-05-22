package br.com.wydmaster.entidade

/**
 * Criado por bruno-meurer em 19/08/17.
 */


class ContaDTO(val id:Int, val usuario:String, val senha:String, val ativo:Boolean, var token:String?, val email:String, val logado:Boolean, val dinheiro:Long) {
}