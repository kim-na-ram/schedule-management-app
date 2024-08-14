package com.bootcamp.schedulemanagementapp.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    SUCCESS("정상 처리되었습니다.", HttpStatus.OK),
    FAIL_REGISTER_SCHEDULE("일정 등록에 실패하였습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_EXIST_SCHEDULE("존재하지 않는 일정입니다.", HttpStatus.NOT_FOUND),
    FAIL_GET_SCHEDULE("일정 조회에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_DATE_FORMAT("잘못된 날짜 형식입니다.", HttpStatus.BAD_REQUEST),
    FAIL_GET_SCHEDULE_LIST("일정 목록 조회에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    MISMATCH_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    FAIL_MODIFY_SCHEDULE("일정 수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_DELETE_SCHEDULE("일정 삭제에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_REGISTER_MANAGER("담당자 등록에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_EXIST_MANAGER("존재하지 않는 담당자입니다.", HttpStatus.NOT_FOUND),
    FAIL_GET_MANAGER("담당자 조회에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_GET_MANAGER_LIST("담당자 목록 조회에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_MODIFY_MANAGER("담당자 수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    ;

    private final String responseMessage;
    private final HttpStatus httpStatus;

    ResponseCode(String responseMessage, HttpStatus httpStatus) {
        this.responseMessage = responseMessage;
        this.httpStatus = httpStatus;
    }
}