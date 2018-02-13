package message;

public enum MessageType {
    CONNECT, DISCONNECT,IS_CONNECT,
    ACCOUNT_REGISTRATION, FAILED_REGISTRATION, DEFAULT,
    AUTHORIZATION, STUDENT_AUTHORIZATION,TEACHER_AUTHORIZATION, ADMIN_AUTHORIZATION, FAILED_AUTHORIZATION,
    ADD_STUDENT,ADD_TEACHER,  ADD_ADMIN,ADD_GROUP,
    ALL_GROUPS, ALL_LESSONS, ALL_ADMINS, ALL_STUDENTS,ALL_TEACHERS,
    DELETE_GROUP,DELETE_ADMIN,  DELETE_TEACHER,DELETE_STUDENT,
    REDACT_TEACHERS, REDACT_GROUPS,REDACT_ADMINS, REDACT_STUDENTS,
    REDACT_ADMIN, REDACT_TEACHER, REDACT_STUDENT, REDACT_LESSONS,
    REDACT_SUCCESS,
    WRONG_ID_TEACHER, WRONG_GROUP_NUMBER,SAME_LOGINS,SAME_PHONE_NUMBERS,SUCCESSFUL,NO_LOGINS,
    WRONG_PASSWORD, GROUP_CONTAINS_STUDENTS, TEACHER_HAS_GROUPS,
    GROUPS_OF_TEACHER, STUDENTS_OF_GROUP, LESSONS_OF_STUDENT, GROUP_INFO, TEACHER_INFO, PAST_TEST_INFO
}