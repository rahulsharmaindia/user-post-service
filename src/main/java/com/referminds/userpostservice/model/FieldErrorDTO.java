package com.referminds.userpostservice.model;

import com.referminds.userpostservice.enums.MessageType;

import lombok.Data;

@Data
public class FieldErrorDTO {
	 
    private String field;
 
    private String message;
    
    private MessageType messageType;

}
