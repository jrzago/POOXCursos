-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 10-Abr-2020 às 22:02
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `poox`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `alunos`
--

CREATE TABLE IF NOT EXISTS `alunos` (
  `id` int(11) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `nascimento` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `alunos`
--

INSERT INTO `alunos` (`id`, `cpf`, `nome`, `telefone`, `email`, `nascimento`) VALUES
(1, '1', 'Zago', '123', 'email@teste.com', '2003-07-30'),
(15, '4', 'Gustavo Fusco Sperandio', '7491274xxx', 'gustavo.sprandio@gmail.com', '1997-05-25'),
(22, '2', '222', '2222', '22222', '1996-08-03');

-- --------------------------------------------------------

--
-- Estrutura da tabela `area`
--

CREATE TABLE IF NOT EXISTS `area` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `nivel_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`),
  KEY `nivel_id` (`nivel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `area`
--

INSERT INTO `area` (`id`, `nome`, `nivel_id`) VALUES
(1, 'Arquitetura e Urbanismo', 5),
(2, 'Beleza e Estética', 5),
(3, 'Certificações em Tecnologia', 5),
(4, 'Comunicação e Artes', 5),
(5, 'Desenvolvimento Social', 5),
(6, 'Design', 5),
(7, 'Educação', 5),
(8, 'Eventos e Lazer', 5),
(9, 'Gastronomia', 5),
(10, 'Gestão Executiva', 5),
(11, 'Gestão e Negócios', 5),
(12, 'Hotelaria e Turismo', 5),
(13, 'Idiomas e Linguagem', 5),
(14, 'Limpeza, Conservação e Zeladoria', 5),
(15, 'Meio Ambiente, Segurança e Saúde no Trabalho', 5),
(16, 'Moda', 5),
(17, 'Saúde e Bem-estar', 5),
(18, 'Tecnologia da Informação', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cursos`
--

CREATE TABLE IF NOT EXISTS `cursos` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `area_id` int(11) NOT NULL,
  `duracao` int(11) NOT NULL,
  `descricao` varchar(1400) DEFAULT NULL,
  `duracao_tipo` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `area_id` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cursos`
--

INSERT INTO `cursos` (`id`, `nome`, `area_id`, `duracao`, `descricao`, `duracao_tipo`) VALUES
(3, '20483B - Programando com C#', 3, 40, 'O participante adquire conhecimento da linguagem C# para desenvolver aplicações para a plataforma.Net. O foco é na estrutura, sintaxe da linguagem e detalhes de implementação do C#. C# foi criado pela Microsoft para ser uma linguagem de programação adequada para o desenvolvimento de aplicações corporativas e combina a produtividade do Visual Basic com o poder do C++.\r\n\r\nCarga horária: 40 horas\r\n\r\nPré-requisito\r\nTer conhecimento de linguagem de programação. É desejável experiência de programação em linguagem C, C++, Visual Basic ou Java.', 0),
(4, 'Agente Cultural', 4, 160, 'Capacita o aluno para realizar diagnóstico e análise de ambientes culturais locais e articular empreendimentos culturais que envolvam comunidade, produtores, financiadores e órgãos públicos. \r\nO curso desenvolve as competências necessárias para que o profissional seja capaz de conhecer e contextualizar as identidades e as culturas locais, articular a produção entre artistas, grupos de manifestações culturais e seu público e estimular a formação de grupos ou coletivos culturais, buscando apoio para essas realizações e promovendo-as entre comunidade, gestores, demais atores e instituições. \r\n\r\nCarga horária: 160 horas\r\n\r\nPré-requisito\r\nIdade mínima: 16 anos\r\nEscolaridade mínima: ensino fundamental 2 incompleto', 0),
(5, 'Agente de Desenvolvimento Local', 5, 160, 'Preparar cidadãos para estruturarem e implementarem ações de desenvolvimento local, em parceria com órgãos dos setores público e privado e da sociedade civil, baseando-se na atuação em rede como referência de sustentabilidade.\r\n\r\nCarga horária: 160 horas\r\n\r\nPré-requisito\r\nIdade mínima: 17 anos\r\nEscolaridade mínima: ensino fundamental 2 incompleto', 0),
(6, 'Arranjos Florais', 6, 12, 'Capacita o aluno para montar arranjos com flores naturais. \r\nO aluno aprende a escolher as flores e folhagens adequadas para pequenos arranjos decorativos, alem de adquirir conhecimentos de manuseio, cuidados gerais e manutenção de arranjos florais.\r\n\r\nCarga horária: 12 horas\r\n\r\nPré-requisito\r\nIdade mínima: 16 anos\r\nEscolaridade mínima: ensino fundamental completo', 0),
(7, 'Como Organizar Informações para um Processo de Aprendizagem Eficaz', 7, 12, 'Neste curso o participante aprende a elaborar mapas mentais claros e conceituais, com o uso de ferramentas específicas que favorecem o processo de ensino-aprendizagem e sua utilização em situações cotidianas.\r\n\r\nCarga horária: 12 horas\r\n\r\nPré-requisito\r\nIdade mínima: 16 anos\r\nEscolaridade mínima: cursando ensino médio', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplina`
--

CREATE TABLE IF NOT EXISTS `disciplina` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `disciplina`
--

INSERT INTO `disciplina` (`id`, `nome`) VALUES
(2, 'C# nivel 1'),
(3, 'JAVA 1'),
(1, 'PHP para iniciantes');

-- --------------------------------------------------------

--
-- Estrutura da tabela `nivel`
--

CREATE TABLE IF NOT EXISTS `nivel` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `nivel`
--

INSERT INTO `nivel` (`id`, `nome`) VALUES
(5, 'CURSOS LIVRES'),
(4, 'CURSOS TÉCNICOS'),
(6, 'EDUCAÇÃO A DISTÂNCIA'),
(3, 'EXTENSÃO UNIVERSITÁRIA'),
(1, 'GRADUAÇÃO'),
(2, 'PÓS-GRADUAÇÃO'),
(7, 'PROGRAMA APRENDIZAGEM');

-- --------------------------------------------------------

--
-- Estrutura da tabela `professores`
--

CREATE TABLE IF NOT EXISTS `professores` (
  `id` int(11) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `nascimento` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `professores`
--

INSERT INTO `professores` (`id`, `cpf`, `nome`, `telefone`, `email`, `nascimento`) VALUES
(1, '12', '1', '1', '1', '1957-10-19'),
(11, '1', 'nome professores', '111111', 'email@email.com', '1957-10-19');

-- --------------------------------------------------------

--
-- Estrutura da tabela `professores_disciplina`
--

CREATE TABLE IF NOT EXISTS `professores_disciplina` (
  `professores_id` int(11) NOT NULL,
  `disciplina_id` int(11) NOT NULL,
  PRIMARY KEY (`professores_id`,`disciplina_id`),
  KEY `disciplina_id` (`disciplina_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `professores_disciplina`
--

INSERT INTO `professores_disciplina` (`professores_id`, `disciplina_id`) VALUES
(1, 1),
(11, 1),
(1, 2),
(11, 2),
(11, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `turmas`
--

CREATE TABLE IF NOT EXISTS `turmas` (
  `id` int(14) NOT NULL,
  `data_inicio` date DEFAULT NULL,
  `data_termino` date DEFAULT NULL,
  `periodo` int(1) DEFAULT '0',
  `segunda` int(1) NOT NULL DEFAULT '0',
  `terca` int(1) NOT NULL DEFAULT '0',
  `quarta` int(1) NOT NULL DEFAULT '0',
  `quinta` int(1) NOT NULL DEFAULT '0',
  `sexta` int(1) NOT NULL DEFAULT '0',
  `sabado` int(1) NOT NULL DEFAULT '0',
  `investimento` decimal(10,2) DEFAULT NULL,
  `informacoes` varchar(1400) DEFAULT NULL,
  `professores_vagas` int(11) DEFAULT NULL,
  `alunos_vagas` int(11) DEFAULT NULL,
  `alunos_bolsas_vagas` int(11) DEFAULT NULL,
  `cursos_id` int(11) NOT NULL,
  `estado` int(1) NOT NULL DEFAULT '0',
  `hora_inicio` int(11) DEFAULT NULL,
  `hora_termino` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cursos_id` (`cursos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `turmas`
--

INSERT INTO `turmas` (`id`, `data_inicio`, `data_termino`, `periodo`, `segunda`, `terca`, `quarta`, `quinta`, `sexta`, `sabado`, `investimento`, `informacoes`, `professores_vagas`, `alunos_vagas`, `alunos_bolsas_vagas`, `cursos_id`, `estado`, `hora_inicio`, `hora_termino`) VALUES
(1, '2018-05-05', '2018-05-05', 1, 1, 1, 0, 0, 0, 0, '16.00', 'insert into turmas (id,data_inicio,data_termino,periodo,segunda,terca,quarta,quinta,sexta,sabado,investimento,informacoes,professores_vagas,alunos_vagas,alunos_bolsas,cursos_id,estado) values ("+fgd+",+dataI+,+dataT+,"+periodo+","+vetDias[0]+","+vetDias[1]+","+vetDias[2]+","+vetDias[3]+","+vetDias[4]+","+vetDias[5]+",+investimento+,+info+,"+vP+","+vA+","+vAB+","+idCurso+",0)', 1, 5, 3, 7, 0, NULL, NULL),
(2, '2021-03-29', '2020-04-29', 1, 0, 1, 0, 0, 0, 0, '1.00', '', 0, 0, 0, 4, 1, NULL, NULL),
(3, '2018-03-31', '2018-03-31', 0, 0, 0, 0, 0, 0, 1, '3.55', 'Modificando turma que a inscrição está aberta.', 0, 0, 0, 3, 2, NULL, NULL),
(5, '2022-05-28', '2020-03-30', 2, 1, 0, 1, 0, 0, 0, '346.86', '\n13,37, 10, 20, 14,17,04\n04,10,13,14,20,37 (17)\n//28=8;29=33(16+8,5+8,5);31=4; T:45\n\n13,37, 10, 20, 14,17,04\n04,10,13,14,20,37 (17)\n//28=8;29=33(16+8,5+8,5);31=4; T:45\n\n13,37, 10, 20, 14,17,04\n04,10,13,14,20,37 (17)\n//28=8;29=33(16+8,5+8,5);31=4; T:45', 3, 5, 3, 5, 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `turmas_alunos`
--

CREATE TABLE IF NOT EXISTS `turmas_alunos` (
  `turmas_id` int(14) NOT NULL,
  `vaga` int(11) NOT NULL,
  `alunos_id` int(11) DEFAULT NULL,
  `bolsa` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`turmas_id`,`vaga`,`bolsa`),
  KEY `alunos_id` (`alunos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `turmas_alunos`
--

INSERT INTO `turmas_alunos` (`turmas_id`, `vaga`, `alunos_id`, `bolsa`) VALUES
(1, 1, NULL, 0),
(1, 1, NULL, 1),
(1, 2, NULL, 0),
(1, 2, NULL, 1),
(1, 3, NULL, 1),
(5, 1, NULL, 0),
(5, 1, NULL, 1),
(5, 2, NULL, 0),
(5, 2, NULL, 1),
(5, 3, NULL, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `turmas_professores`
--

CREATE TABLE IF NOT EXISTS `turmas_professores` (
  `turmas_id` int(14) NOT NULL,
  `vaga` int(11) NOT NULL,
  `professores_id` int(11) DEFAULT '0',
  PRIMARY KEY (`turmas_id`,`vaga`),
  KEY `professores_id` (`professores_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `turmas_professores`
--

INSERT INTO `turmas_professores` (`turmas_id`, `vaga`, `professores_id`) VALUES
(1, 1, NULL),
(5, 1, NULL),
(5, 2, NULL),
(5, 3, NULL);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `area`
--
ALTER TABLE `area`
  ADD CONSTRAINT `area_ibfk_1` FOREIGN KEY (`nivel_id`) REFERENCES `nivel` (`id`);

--
-- Limitadores para a tabela `cursos`
--
ALTER TABLE `cursos`
  ADD CONSTRAINT `cursos_ibfk_1` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`);

--
-- Limitadores para a tabela `professores_disciplina`
--
ALTER TABLE `professores_disciplina`
  ADD CONSTRAINT `professores_disciplina_ibfk_1` FOREIGN KEY (`professores_id`) REFERENCES `professores` (`id`),
  ADD CONSTRAINT `professores_disciplina_ibfk_2` FOREIGN KEY (`disciplina_id`) REFERENCES `disciplina` (`id`);

--
-- Limitadores para a tabela `turmas`
--
ALTER TABLE `turmas`
  ADD CONSTRAINT `turmas_ibfk_1` FOREIGN KEY (`cursos_id`) REFERENCES `cursos` (`id`);

--
-- Limitadores para a tabela `turmas_alunos`
--
ALTER TABLE `turmas_alunos`
  ADD CONSTRAINT `turmas_alunos_ibfk_1` FOREIGN KEY (`turmas_id`) REFERENCES `turmas` (`id`),
  ADD CONSTRAINT `turmas_alunos_ibfk_2` FOREIGN KEY (`alunos_id`) REFERENCES `alunos` (`id`);

--
-- Limitadores para a tabela `turmas_professores`
--
ALTER TABLE `turmas_professores`
  ADD CONSTRAINT `turmas_professores_ibfk_1` FOREIGN KEY (`turmas_id`) REFERENCES `turmas` (`id`),
  ADD CONSTRAINT `turmas_professores_ibfk_2` FOREIGN KEY (`professores_id`) REFERENCES `professores` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
