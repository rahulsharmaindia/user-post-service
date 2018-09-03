package com.referminds.userpostservice.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ErrorDTO {

	private List<FieldErrorDTO> errorDTOs = new ArrayList<>();

}