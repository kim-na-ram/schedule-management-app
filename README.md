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
  | 0 | managerId | Long | O | 담당자 고유번호 |
  | 0 | password | String | O | 비밀번호 |

- Response Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | scheduleId | Long | O | 스케줄 고유번호 |
  | 0 | contents | String | O | 할 일 내용 |
  | 0 | managerId | Long | O | 담당자 고유번호 |
  | 0 | regDate | String | O | 작성일 (yyyy-MM-dd HH:mm:ss) |
  | 0 | updateDate | String | O | 수정일 (yyyy-MM-dd HH:mm:ss) |

- 응답 코드

  | Result Message | HTTP Status |
  | --- | --- |
  | 정상 처리되었습니다. | 200 |
  | 존재하지 않는 담당자입니다. | 404 |
  | 일정 등록에 실패하였습니다. | 500 |

<br>

#### Request Json
```json
{
  "contents": "string",
  "managerId": 1,
  "password": "string"
}
```

#### Response Json
```json
{
  "scheduleId": 1,
  "contents": "string",
  "managerId": 1,
  "regDate": "2024-08-14 22:08:55",
  "updateDate": "2024-08-14 22:08:55"
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
  | 0 | managerId | Long | O | 담당자 고유번호 |
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
  "scheduleId": 1,
  "contents": "string",
  "managerId": 1,
  "managerName": "string",
  "regDate": "2024-08-14 22:08:55",
  "updateDate": "2024-08-14 22:08:55"
}
```

<br>
## 일정 목록 조회
URL : `GET` `/schedules` 

- Query Parameter

  | depth | 변수명 | 타입 | 필수 여부 | 비고               |
  | --- | --- | --- | --- |------------------|
  | 0 | pageNumber | Integer | X | 페이지 번호           |
  | 0 | pageSize | Integer | X | 페이지 크기           |
  | 0 | updateDate | String | X | 수정일 (yyyy-MM-dd) |
  | 0 | managerId | Long | X | 담당자 고유번호         |

- Response Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | scheduleList | List\<Object\> | O | 스케줄 목록 |
  | 1 | scheduleId | Long | O | 스케줄 고유번호 |
  | 1 | contents | String | O | 스케줄 내용 |
  | 1 | managerId | Long | O | 담당자 고유번호 |
  | 1 | managerName | String | O | 담당자명 |
  | 1 | regDate | String | O | 작성일 |
  | 1 | updateDate | String | X | 수정일 |

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
      "managerId": 1,
      "managerName": "string",
      "regDate": "2024-08-14 22:08:56",
      "updateDate": "2024-08-14 22:08:56"
    },
    {
      "scheduleId": 1,
      "contents": "string",
      "managerId": 1,
      "managerName": "string",
      "regDate": "2024-08-14 22:08:55",
      "updateDate": "2024-08-14 22:08:55"
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
  | 0 | contents | String | X | 할 일  |
  | 0 | managerId | Long | X | 담당자 고유번호 |
  | 0 | password | String | O | 비밀번호 |

- Response Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | scheduleId | Long | O | 스케줄 고유번호 |
  | 0 | contents | String | O | 스케줄 내용 |
  | 0 | managerId | Long | O | 담당자 고유번호 |
  | 0 | managerName | String | O | 담당자명 |
  | 0 | password | String | O | 비밀번호 |
  | 0 | regDate | String | O | 작성일 |
  | 0 | updateDate | String | O | 수정일 |

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
  "contents": "modify string",
  "managerId": 1,
  "password": "string"
}
```

#### Response Json
```json
{
  "scheduleId": 1,
  "contents": "modify string",
  "managerId": 1,
  "managerName": "tester",
  "regDate": "2024-08-14 22:08:55",
  "updateDate": "2024-08-14 22:37:38"
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

<br>

## 담당자 등록
URL : `POST` `/managers/manager` 

- Request Header

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |

- Request Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | name | String | O | 담당자명 |
  | 0 | email | String | X | 담당자 이메일 |

- 응답코드

  | Result Message | HTTP Status |
  | --- | --- |
  | 정상 처리되었습니다. | 200 |
  | 잘못된 이메일 형식입니다. | 400 |
  | 담당자 등록에 실패하였습니다. | 500 |


<br>

#### Request Json
```json
{
  "name": "string",
  "email": "string@string.com"
}
```

#### Response Json
```json
{
  "managerId": 1,
  "name": "string",
  "email": "string@string.com",
  "regDate": "2024-08-14 23:07:38",
  "updateDate": "2024-08-14 23:07:38"
}
```

<br>

## 담당자 조회
URL : `GET` `/managers/{managerId}`

- Path Variable

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | managerId | Long | O | 담당자 고유번호 |

- Response Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | managerId | Long | O | 담당자 고유번호 |
  | 0 | name | String | O | 담당자명 |
  | 0 | email | String | X | 담당자 이메일 |
  | 0 | regDate | String | O | 작성일 |
  | 0 | updateDate | String | O | 수정일 |

- 응답 코드

  | Result Message | HTTP Status |
  | --- | --- |
  | 정상 처리되었습니다. | 200 |
  | 존재하지 않는 담당자입니다. | 404 |
  | 담당자 조회에 실패하였습니다. | 500 |

<br>

#### Response Body
```json
{
  "managerId": 1,
  "name": "string",
  "email": "string@string.com",
  "regDate": "2024-08-14 23:07:38",
  "updateDate": "2024-08-14 23:07:38"
}
```

<br>

## 담당자 목록 조회
URL : `GET` `/managers`

- Response Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | managerList | List\<Object\> | O | 담당자 목록 |
  | 1 | managerId | Long | O | 담당자 고유번호 |
  | 1 | name | String | O | 담당자명 |
  | 1 | email | String | X | 담당자 이메일 |
  | 1 | regDate | String | O | 작성일 |
  | 1 | updateDate | String | O | 수정일 |

- 응답 코드

  | Result Message | HTTP Status |
  | --- | --- |
  | 정상 처리되었습니다. | 200 |
  | 담당자 목록 조회에 실패하였습니다. | 500 |

<br>

#### Response Json
```json
{
  "managerList": [
    {
      "managerId": 1, 
      "name": "string",
      "email": "string@string.com",
      "regDate": "2024-08-14 23:07:38",
      "updateDate": "2024-08-14 23:07:38"
    },
    {
      "managerId": 2,
      "name": "string",
      "email": "string@string.com",
      "regDate": "2024-08-14 23:07:43",
      "updateDate": "2024-08-14 23:07:43"
    }
  ]
}
```

<br>

## 담당자 수정
URL : `PATCH` `/manager/{managerId}`

- Path Variable

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | picId | Long | O | 담당자 고유번호 |

- Request Body

  | depth | 변수명 | 타입 | 필수 여부 | 비고 |
  | --- | --- | --- | --- | --- |
  | 0 | name | String | X | 담당자명 |
  | 0 | email | String | X | 담당자 이메일 |

- 응답 코드

  | Result Message | HTTP Status |
  | --- | --- |
  | 정상 처리되었습니다. | 200 |
  | 잘못된 이메일 형식입니다. | 400 |
  | 존재하지 않는 담당자입니다. | 404 |
  | 담당자 수정에 실패하였습니다. | 500 |

<br>

#### Request Json
```json
{
  "name": "modifyString",
  "email": "modifyString@string.com"
}
```