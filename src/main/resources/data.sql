insert into Aluno values (59320492,'carlos eduardo');
insert into Aluno values (11113,'breno');

insert into ATIVIDADE(NOME, PERCENTUAL_CARGA_HORARIA, MAXIMO_ATIVIDADES_SEMESTRE, PERCENTUAL_POR_ATIVIDADE)
values ('Monitoria', 150, 1, 50),
('Disciplinas Extracurriculares', 50, 1, 40),
('Participação em órgãos colegiados/conselhos', 30, 1, 10),
('Integrante de Núcleo ou Grupo de Estudo', 100, 3, 20),
('Cursos e mini-cursos', 20, 2, 40);

INSERT INTO LANCAMENTO_ATIVIDADE ( ALUNO_RA,  ATIVIDADE_CODIGO, DATA_INICIO, DATA_FIM, QUANTIDADE_HORAS)
VALUES ( 11113,  1,  '2018-11-18',  '2018-12-18', 50), 
( 11113,  3,  '2018-06-18',  '2018-12-18', 50), 
( 11113,  3,  '2018-05-18',  '2018-10-18', 50), 
( 59320492, 4,  '2018-03-18',  '2018-06-18', 50), 
( 59320492,  5,  '2018-01-18',  '2018-09-18', 50);