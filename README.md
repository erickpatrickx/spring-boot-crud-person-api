
Para rodar a aplicação:
1. Importe o projeto ( File -> Import -> Existing Maven Project)
2. O Eclipse vai atualizar as dependencias
3. Crie um banco mysql [9] com nome contato.
4. As configurações do banco ficam no arquivo application.properties faço o apontamento para o seu banco
5. Rode a aplicação (procure a clase SpringBootWebApplication clique botão oposto em cima dela e va até Run as -> Java Aplication)
6. O projeto cria o banco sozinho
4. Cadastre no banco na tabela ROLE (1 - ROLE_ADMIN e 2 - ROLE_CONTATO)
5. se estiver tudo ok acesse http://localhost:8080/
6. existe uma senha padrão de adm - Login: admin Senha:password

Obs.

Pode ser que tenha que configurar a JDK 


Referências:

[1] Spring MVC 4 - Framework Java para Aplicações Web MVC - https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html

[2] Spring Boot 1 - É um Framework Java (baseado na Plataforma Spring) para Aplicações web que usam inversão de contêiner de controle para a plataforma Java. https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-security

[3] Thymeleaf - É um engine de modelo Java XML / XHTML / HTML5 que pode funcionar tanto em ambientes da Web (baseados em Servlet) quanto em ambientes não-web. É mais adequado para servir XHTML / HTML5 na camada de visualização de aplicativos da web baseados em MVC, mas pode processar qualquer arquivo XML mesmo em ambientes off-line. Ele fornece integração completa do Spring Framework. https://www.thymeleaf.org

[4] Bootstrap - Framework para Aplicações Web responsiva - https://v4-alpha.getbootstrap.com/getting-started/introduction

[5] JQuery - Biblioteca de Funções JavaScript - https://jquery.com/

[6] ORM JPA - Abstarçaõ de Acesso a Dados - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

[7] Spring Security - É um framework Java que prover uma estrutura de controle de acesso para aplicações Java/Java EE que fornece autenticação, autorização e outros recursos de segurança para aplicativos corporativos. https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/

[8] Maven - Gestão de Builds e Dependências - https://maven.apache.org

[9] Mysql 5 - Sistema de Gerenciamento de Banco de Dados - https://dev.mysql.com/downloads/mysql/


