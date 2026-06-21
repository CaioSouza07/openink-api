T1
Endpoint: POST /posts/{postId}/likes
Descrição: Remove o like quando o usuário já curtiu o post.

T2
Endpoint: POST /posts/{postId}/likes
Descrição: Adiciona um like quando o usuário ainda não curtiu o post.

T3
Endpoint: GET /posts/{postId}/likes
Descrição: Retorna o número total de likes de um post.

T4
Endpoint: GET /contents/{id}
Descrição: Retorna o conteúdo pelo ID quando encontrado.

T5
Endpoint: GET /contents/post/{idPost}
Descrição: Lança NotFoundException quando não existir conteúdo para o post informado.

T6
Endpoint: POST /post (usado indiretamente pela criação de conteúdo)
Descrição: Cria o conteúdo associado a um post e retorna os dados salvos.

T7
Endpoint: (nenhum endpoint público; método de serviço interno)
Descrição: Remove o conteúdo chamando o repositório correspondente.

T8
Endpoint: POST /report
Descrição: Cria um relatório para um post existente e retorna o relatório salvo.

T9
Endpoint: POST /report
Descrição: Lança NotFoundException quando o post informado não existir ao tentar reportar.

T10
Endpoint: POST /auth (gera token)
Descrição: Gera um token JWT para o usuário e valida retornando o subject (nome do usuário).

T11
Endpoint: (validação usada internamente pela infra de segurança)
Descrição: Lança TokenJWTException ao validar um token JWT inválido.

T12
Endpoint: POST /post
Descrição: Salva um post e cria o conteúdo relacionado.

T13
Endpoint: GET /post
Descrição: Retorna uma página de posts com paginação.

T14
Endpoint: GET /post/{id}
Descrição: Retorna o post pelo ID quando encontrado.

T15
Endpoint: GET /post/{id}
Descrição: Lança NotFoundException quando o post com o ID informado não for encontrado.

T16
Endpoint: (nenhum)
Descrição: Verifica se o contexto da aplicação carrega corretamente.

T17
Endpoint: POST /auth
Descrição: Autentica usuário existente e retorna token JWT.

T18
Endpoint: POST /auth
Descrição: Cria um novo usuário quando não existir e retorna token JWT.

T19
Endpoint: POST /auth (gera token)
Descrição: Gera corretamente o token JWT para um usuário.

T20
Endpoint: (validação usada internamente pela infra de segurança)
Descrição: Valida corretamente o token JWT e retorna o subject.

T21
Endpoint: GET /user/{id}
Descrição: Retorna os dados de um usuário existente.

T22
Endpoint: GET /user/{id}
Descrição: Lança NotFoundException quando o usuário solicitado não existir.
