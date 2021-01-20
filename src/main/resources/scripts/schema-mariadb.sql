DROP TABLE IF EXISTS BOARD;

create table BOARD
(
    BOARD_ID VARCHAR(64) not null comment '게시물ID',
    TITLE VARCHAR(100) not null comment '제목',
    CONTENTS TEXT(5000) null comment '내용',
    WRITER VARCHAR(50) null comment '작성자',
    PASSWORD VARCHAR(32) null comment '비밀번호',
    CREATION_DATE TIMESTAMP null comment '작성일시',
    DATE_OF_VERSION TIMESTAMP null comment '수정일시'
)
comment '게시판';

alter table BOARD add constraint BOARD_pk primary key (BOARD_ID);