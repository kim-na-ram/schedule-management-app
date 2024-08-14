# 일정 관리 앱 서버

# ERD
![ERD](https://github.com/user-attachments/assets/c0579f99-9250-4c44-9d24-aed4f6a13aa2)

<br>

# API 명세서
## 일정 등록
URL : `POST` `/schedules/schedule`

- Request Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | contents | String | O | 할 일 내용 |
  | 0 | managerName | String | O | 담당자명 |
  | 0 | password | String | O | 비밀번호 |

- Response Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | scheduleId | Long | O | 스케줄 고유번호 |
  | 0 | contents | String | O | 할 일 내용 |
  | 0 | managerName | String | O | 담당자명 |
  | 0 | regDate | String | O | 작성일 (yyyy-MM-dd HH:mm:ss) |
  | 0 | updateDate | String | O | 수정일 (yyyy-MM-dd HH:mm:ss) |

- 응답 코드

  | Result Message | HTTP Status |
  | --- | --- |
  | 정상 처리되었습니다. | 200 |
  | 일정 등록에 실패하였습니다. | 500 |

<br>

#### Request Json
```json
{
  "contents": "string",
  "managerName": "string",
  "password": "string"
}
```

#### Response Json
```json
{
  "scheduleId": 2,
  "contents": "string",
  "managerName": "string",
  "regDate": "2024-08-12 01:56:42",
  "updateDate": "2024-08-12 01:56:42"
}
```
<br>

## 일정 조회
URL : `GET` `/schedules/{scheduleId}`

- Path Variable

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | scheduleId | Long | O | 스케줄 고유번호 |

- Response Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | scheduleId | Long | O | 스케줄 고유번호 |
  | 0 | contents | String | O | 할 일 내용 |
  | 0 | managerName | String | O | 담당자명 |
  | 0 | regDate | String | O | 작성일 (yyyy-MM-dd HH:mm:ss) |
  | 0 | updateDate | String | O | 수정일 (yyyy-MM-dd HH:mm:ss) |

- 응답 코드

  | Result Message | HTTP Status |
  | --- | --- |
  | 정상 처리되었습니다. | 200 |
  | 존재하지 않는 일정입니다. | 404 |
  | 일정 조회에 실패하였습니다. | 500 |

<br>

#### Response Json
```json
{
  "scheduleId": 2,
  "contents": "string",
  "managerName": "string",
  "regDate": "2024-08-12 01:56:42",
  "updateDate": "2024-08-12 01:56:42"
}
```
<br>

## 일정 목록 조회
URL : `GET` `/schedules`

- Query Parameter

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | editDate | Date | X | 수정일 |
  | 0 | managerName | String | X | 담당자명 |

- Response Body

  | depth | 변수명 | 타입             | 필수 여부 | 비고 |
  | --- | --- |----------------| --- | --- |
  | 0 | scheduleList | List\<Object\> | O | 스케줄 목록 |
  | 1 | scheduleId | Long           | O | 스케줄 고유번호 |
  | 1 | contents | String         | O | 할 일 내용 |
  | 1 | managerName | String         | O | 담당자명 |
  | 1 | regDate | String         | O | 작성일 (yyyy-MM-dd HH:mm:ss) |
  | 1 | updateDate | String         | O | 수정일 (yyyy-MM-dd HH:mm:ss) |

- 응답 코드

  | Result Message | HTTP Status |
  | --- | --- |
  | 정상 처리되었습니다. | 200 |
  | 잘못된 날짜 형식입니다. | 400 |
  | 일정 목록 조회에 실패하였습니다. | 500 |

<br>

#### Response Json
```json
{
  "scheduleList": [
    {
      "scheduleId": 2,
      "contents": "string",
      "managerName": "string",
      "regDate": "2024-08-12 01:56:42",
      "updateDate": "2024-08-12 01:56:42"
    }
  ]
}
```

<br>

## 일정 수정
URL : `PATCH` `/schedules/{scheduleId}`

- Path Variable

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | scheduleId | Long | O | 스케줄 고유번호 |

- Request Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | password | String | O | 비밀번호 |
  | 0 | contents | String | X | 할 일  |
  | 0 | managerName | String | X | 담당자명 |

- Response Body

  | depth | 변수명        | 타입 | 필수 여부 | 비고                         |
  | --- | --- | --- | --- | --- |
  | 0 | scheduleId | Long | O | 스케줄 고유번호                   |
  | 0 | contents   | String | O | 스케줄 내용                     |
  | 0 | managerName | String | O | 담당자명                       |
  | 0 | password   | String | O | 비밀번호                       |
  | 0 | regDate    | Date | O | 작성일 (yyyy-MM-dd HH:mm:ss)  |
  | 0 | updateDate | Date | O | 수정일 (yyyy-MM-dd HH:mm:ss)  |

- 응답 코드

  | Result Message | HTTP Status |
  | --- | --- |
  | 정상 처리되었습니다. | 200 |
  | 비밀번호가 일치하지 않습니다. | 401 |
  | 존재하지 않는 일정입니다. | 404 |
  | 일정 수정에 실패하였습니다. | 500 |

<br>

#### Request Json
```json
{
  "contents": "string",
  "managerName": "string",
  "password": "string"
}
```

#### Response Json

```json
{
  "scheduleId": 2,
  "contents": "string",
  "managerName": "test",
  "regDate": "2024-08-12 01:56:42",
  "updateDate": "2024-08-12 10:59:43"
}
```

<br>

## 일정 삭제
URL : `DELETE` `/schedules/{scheduleId}`

- Path Variable

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | scheduleId | Long | O | 스케줄 고유번호 |

- Request Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | password | String | O | 비밀번호 |

- 응답 코드

  | Result Message | HTTP Status |
  | --- | --- |
  | 정상 처리되었습니다. | 200 |
  | 비밀번호가 일치하지 않습니다. | 401 |
  | 존재하지 않는 일정입니다. | 404 |
  | 일정 삭제에 실패하였습니다. | 500 |

<br>

#### Request Json

```json
{
  "password": "string"
}
```
