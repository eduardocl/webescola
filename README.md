webescola
=========

v0.0.3

Implementação do cadastro de disciplinas (classes de domínio e negócio), utilizando a metodologia de TDD, onde primeiro escrevemos os 
testes para posteriormente escrevermos o código de produção. Utilizaremos as facilidades do demoiselle para logging e execução dos 
unit tests.

Conceitos abordados

* Implementando uma camada de persistência (JPATransaction ou JTATransaction?) - extensão demoiselle-jsf
* Implementando a camada de negócio (@BusinessController)
* Camada de apresentação JSF 2 (prime faces) - extensão demoiselle-jsf 
* Inicializadores e finalizadores @Startup e @Shutdown
* Especificando arquivo de resource bundle com @Name