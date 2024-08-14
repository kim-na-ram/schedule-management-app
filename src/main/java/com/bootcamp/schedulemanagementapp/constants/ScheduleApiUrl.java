package com.bootcamp.schedulemanagementapp.constants;

public class ScheduleApiUrl {
    public static final String SCHEDULE_BASE_URL = "/schedules";

    // 스케줄 등록
    public static final String REGISTER_SCHEDULE = "/schedule";

    // 스케줄 단건 조회, 스케줄 수정, 스케줄 삭제
    public static final String SCHEDULE_ID = "/{scheduleId}";
}
