package com.grab.resume.dto.response;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.grab.resume.dto.Resume;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeMgmtResponse {
	
	private Boolean success;
	private Long resumeId;
	private Resume resume;
	private String errorMsg;
	private List<Resume> resumes;
	
	public ResumeMgmtResponse() {
		
	}
	
	public ResumeMgmtResponse(Boolean success) {
		this.success = success;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Long getResumeId() {
		return resumeId;
	}
	public void setResumeId(Long resumeId) {
		this.resumeId = resumeId;
	}
	public Resume getResume() {
		return resume;
	}
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<Resume> getResumes() {
		return resumes;
	}

	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}

}
