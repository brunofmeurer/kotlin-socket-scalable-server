package br.com.wydmaster.estrutura

/**
 * Criado por bruno-meurer em 27/08/17.
 */
enum class TipoDeEnvioPacoteEnum {
    SOMENTE_O_REMETENTE,
    SOMENTE_O_DESTINATARIO,
    REMETENTE_E_DESTINATARIO,
    GRUPO_SELETO,
    TODOS_EM_VOLTA,
    TODOS_DO_SERVIDOR;
}