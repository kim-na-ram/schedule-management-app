# 테이블 생성
CREATE TABLE schedule(
    schedule_id     INT             PRIMARY KEY AUTO_INCREMENT,
    contents        VARCHAR(255)    NOT NULL,
    manager_name    VARCHAR(255)    NOT NULL,
    password        VARCHAR(255)    NOT NULL,
    reg_date        TIMESTAMP       NOT NULL,
    update_date     TIMESTAMP       NOT NULL,
    is_deleted      TINYINT         NOT NULL DEFAULT 0
);

# 테이블 구조 확인
DESC schedule;