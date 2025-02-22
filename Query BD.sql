create database biblioteca;

use biblioteca;

create table usuario_atual(
id_usuario					integer primary key not null,
nome 						varchar(50) not null,
email						varchar(50) not null,
senha						varchar(100) not null,
is_admin					boolean not null
);

insert into usuario_atual ( id_usuario, nome, email, senha, is_admin) values (1, "Isaque Barbosa Alves", "iba2@aluno.ifal.edu.br", "senha_confiavel", true);

create table Usuarios(
id_usuario					integer auto_increment primary key not null,
nome						varchar(50),
email_institucional			varchar(50),
senha						varchar(100),
is_admin					boolean
);


create table livros_biblioteca(
id_livro					integer auto_increment primary key not null,
autor						varchar(31) not null,
titulo						varchar(31) not null,
sinopse						varchar(2000) not null,
genero						varchar(31) not null,
nota						integer not null
);

create table Exemplares(
id_exemplar					integer auto_increment primary key not null,
id_livro_fk					integer,
foreign key(id_livro_fk) references livros_biblioteca(id_livro)
);

create table Emprestimos(
id_emprestimo				integer auto_increment primary key not null,
id_exemplar_fk				integer,
situacao					varchar(20),
foreign key(id_exemplar_fk) references Exemplares(id_exemplar),
id_usuario_fk				integer,
foreign key(id_usuario_fk) references Usuarios(id_usuario),
id_livro_fk					integer not null,
foreign key (id_livro_fk) references livros_biblioteca(id_livro),
data_emprestimo				date,
data_devolucao				date
);

insert into Usuarios ( nome, email_institucional, senha, is_admin) values ("Isaque Barbosa Alves", "iba2@aluno.ifal.edu.br", "senha_confiavel", true);
insert into Usuarios ( nome, email_institucional, senha, is_admin) values ("Emilly Mayara de Brito Santos", "embs5@aluno.ifal.edu.br", "senha_confiavel", true);
insert into Usuarios ( nome, email_institucional, senha, is_admin) values ("João Pedro da Conceição Araújo", "jpca6@aluno.ifal.edu.br", "senha_confiavel", true);
insert into Usuarios ( nome, email_institucional, senha, is_admin) values ("Isabela Ferreira Silva", "ifs6@aluno.ifal.edu.br", "senha_confiavel", true);
insert into Usuarios ( nome, email_institucional, senha, is_admin) values ("Maria Vitória dos Santos Paz", "mvsp2@aluno.ifal.edu.br", "senha_confiavel", true);

select * from livros_biblioteca;
select * from usuario_atual;
select * from usuarios;
select * from exemplares;
select * from emprestimos;
