create table member(
 	id number primary key,
 	name varchar2(20) not null,
 	phone varchar2(20) not null,
 	team varchar2(20) not null   
);

create sequence seq_mem;

insert into member values (seq_mem.nextval, 'ȫ�浿', '010-8888-9999', '����');
insert into member values (seq_mem.nextval, '��ȣ��', '010-7575-7575', 'MC');