package com.bazinga.hakaton.dto;

import java.util.List;

public class EducationalUnitDto {
	private Long id;
	private String name;
	private List<SubjectDto> subjects;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SubjectDto> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<SubjectDto> subjects) {
		this.subjects = subjects;
	}
}
