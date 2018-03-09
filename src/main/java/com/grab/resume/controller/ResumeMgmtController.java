package com.grab.resume.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grab.resume.dto.Resume;
import com.grab.resume.dto.response.ResumeMgmtResponse;
import com.grab.resume.exception.InvalidInputException;
import com.grab.resume.service.ResumeMgmtService;

@RestController
@RequestMapping("/resumemanagement/api/v1")
@CrossOrigin("*")
public class ResumeMgmtController {

	private static final Logger logger = LoggerFactory.getLogger(ResumeMgmtController.class);

	@Autowired
	private ResumeMgmtService resumeMgmtService;

	@PostMapping("/uploadResumeDetails")
	public ResponseEntity<ResumeMgmtResponse> uploadResume(@Valid @RequestBody Resume resumeDto) {

		logger.info("addResumeDetails API invoked.");
		logger.debug("Request: " + resumeDto);

		// Name is mandatory
		if (resumeDto.getName() == null) {
			throw new InvalidInputException("Name is mandatory");
		}

		Long resumeId = resumeMgmtService.uploadResume(resumeDto);

		ResumeMgmtResponse response = new ResumeMgmtResponse(true);
		response.setResumeId(resumeId);
		return new ResponseEntity<ResumeMgmtResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/getResume/{resumeId}")
	public ResponseEntity<ResumeMgmtResponse> getResumeById(@PathVariable Long resumeId) {
		logger.info("getResumeById API invoked.");
		logger.debug("Resume Id : " + resumeId);

		Resume resume = resumeMgmtService.getResumeDetailsById(resumeId);

		ResumeMgmtResponse response = new ResumeMgmtResponse(true);
		response.setResume(resume);
		return new ResponseEntity<ResumeMgmtResponse>(response, HttpStatus.OK);
	}

}
