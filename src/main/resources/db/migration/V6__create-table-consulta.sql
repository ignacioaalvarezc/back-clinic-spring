create table consulta(
    id bigint not null auto_increment,
    medico_id bigint not null,
    paciente_id bigint not null,
    fecha datetime not null,
    motivo_cancelamiento varchar(100),
    primary key(id),
    constraint fk_consulta_medico_id foreign key(medico_id) references doctor(id),
    constraint fk_consulta_paciente_id foreign key(paciente_id) references patient(id)
);