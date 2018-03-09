package com.grab.resume.service;

import com.grab.resume.dto.Resume;

public interface ResumeMgmtService {

	Long uploadResume(Resume resumeDto);

	Resume getResumeDetailsById(Long resumeId);

}
