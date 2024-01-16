create table if not exists tb_cliente
(
    id                              bigint auto_increment,
    created_at                      date,
    updated_at                      date,
    nome_cliente                    varchar(255) not null,
    primary key (id));

create table if not exists tb_pedido
(
    id                              bigint auto_increment,
    created_at                      date not null,
    updated_at                      date,
    cliente_id                      bigint not null,
    valor_pedido                    decimal(10,2),
    valor_com_desconto              decimal(10,2),
    desconto                        decimal(10,2),
    primary key (id));

create table if not exists tb_produto
(
    id                              bigint auto_increment,
    created_at                      date,
    updated_at                      date,
    nome_produto                    varchar(255),
    primary key (id));

create table if not exists rl_produto_pedido
(
    pedido_id                       bigint not null,
    produto_id                      bigint not null,
    quantidade                      integer not null,
    valor_unitario                  decimal(10,2) not null,
    primary key (pedido_id, produto_id));

alter table tb_pedido
    add constraint FKajo6v90obpung9h40lcain479 foreign key (cliente_id) references tb_cliente (id);
alter table rl_produto_pedido
    add constraint FKd9h6gs0pwudluhbfahmmewh3q foreign key (pedido_id) references tb_pedido (id);
alter table rl_produto_pedido
    add constraint FKi00waov6f7ytwv761u09fy8a1 foreign key (produto_id) references tb_produto (id);