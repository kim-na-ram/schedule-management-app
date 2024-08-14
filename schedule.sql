# 테이블 생성
# 일정 데이터베이스 생성
CREATE TABLE schedule(
    schedule_id     INT             PRIMARY KEY AUTO_INCREMENT,
    contents        VARCHAR(255)    NOT NULL,
    manager_id      INT             NOT NULL,
    password        VARCHAR(255)    NOT NULL,
    reg_date        TIMESTAMP       NOT NULL,
    update_date     TIMESTAMP       NOT NULL,
    is_deleted      TINYINT         NOT NULL DEFAULT 0,
    FOREIGN KEY(manager_id) REFERENCES manager(manager_id) ON UPDATE CASCADE ON DELETE CASCADE
);

# 담당자 데이터베이스 생성
CREATE TABLE manager(
    manager_id      INT             PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(255)    NOT NULL,
    email           VARCHAR(255),
    reg_date        TIMESTAMP       NOT NULL,
    update_date     TIMESTAMP
);

# 테이블 구조 확인
DESC schedule;
DESC manager;