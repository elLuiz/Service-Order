package com.works.soworks.soworksapi.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Exception {
    private Integer status;
    private OffsetDateTime time;
    private String message;
    private List<Field> fields;

    public static class Field{
        private String fieldName;
        private String fieldMessage;

        public Field(String fieldName, String fieldMessage) {
            this.fieldName = fieldName;
            this.fieldMessage = fieldMessage;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldMessage() {
            return fieldMessage;
        }

        public void setFieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
