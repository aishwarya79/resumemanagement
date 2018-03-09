package com.grab.resume.service.impl;

import static com.grab.resume.utils.ResumeUtils.resumeStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.grab.resume.dto.Resume;
import com.grab.resume.exception.DataNotFoundException;
import com.grab.resume.service.ResumeMgmtService;
import com.grab.resume.utils.ResumeUtils;

@Service
public class ResumeMgmtServiceImpl implements ResumeMgmtService {

	private static final Logger logger = LoggerFactory.getLogger(ResumeMgmtServiceImpl.class);

	@Override
	public Long uploadResume(Resume resume) {

		Long resumeId = ResumeUtils.generateResumeId();
		logger.debug("Generated resumeId : " + resumeId);
		resumeStore.put(resumeId, resume);
		logger.info("Resume Details saved successfully");
		return resumeId;
	}

	@Override
	public Resume getResumeDetailsById(Long resumeId) {
		Resume resume = resumeStore.get(resumeId);

		if (resume == null) {
			logger.debug("No data found for " + resumeId);
			StringBuilder message = new StringBuilder("No data found for ");
			message.append(resumeId);
			throw new DataNotFoundException(message.toString());
		}

		logger.info("Data found for ResumeId: " + resumeId);
		return resume;
	}

}
