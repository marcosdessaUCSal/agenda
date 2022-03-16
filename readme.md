# Projeto Java Web

Esta uma adaptação de um trabalho de autoria de José de Assis, apresentado ao longo de uma playlist no YouTube disponível em

https://www.youtube.com/playlist?list=PLbEOwbQR9lqz9AnwhrrOLz9cz1-TxoiUg

Este projeto é proposto como atividade didática por nós, alunos do Prof. Antônio Cláudio Pedreira Neiva, na disciplina Testes e Qualidade de Software, no curso de ADS da UCSal.

## A Aplicação
Trata-se de um projeto web Maven em Eclipse, com back-end em Java e banco de dados MySQL, que implementa uma agenda de contatos.
Utiliza:
- Servidor Tomcat 9.0
- JSP
- MariaDB 3.0.3
- JDBC

## Script do BD:
CREATE DATABASE `dbagenda` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `contatos` (
  `idcon` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `fone` varchar(15) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idcon`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

O user e o password estão configurados na classe DAO.java, e devem estar de acordo com o BD.

## Alunos
- Marcos Dessa de Oliveira
- Misael Ferreira Suzarte dos Santos
- Vinicius Silva Oliveira
- Emily Nascimento Souza Ferreira
- Heider da Silva Mascarenhas
- Matheus Maia Carvalho