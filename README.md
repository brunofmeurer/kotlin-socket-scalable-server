# kotlin-socket-scalable-server
Servidor modular feito em kotlin projetado para receber e enviar pacotes de forma relativa e escalável.

# Modulo entrada
Neste módulo temos todo a estrutura de input/output dos pacotes do servidor.
Foi estruturado para apenas fazer este trabalho e não deve conter nenhum processamento.

# Modulo persistência
Neste modulo é preparado toda e qualquer necessária conexão com o banco de dados. (Não é obrigatório).

# Modulo de processamento
É aqui que a magica acontece. Onde o processamento de dados ocorre, calculo e preparo dos pacotes. É possível instanciar mais de uma execução em outras maquinas onde o próprio modulo de entrada irá gerênciar para qual instância mandará o processo.

# Quando utilizar?
Este servidor pode ser utilizado para o desenvolvimento de serviços para jogos e qualquer aplicação que exige troca de informações realtime.
