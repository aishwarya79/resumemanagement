package com.grab.resume.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.grab.resume.dto.Resume;

public class ResumeUtils {

	public static Map<Long, Resume> resumeStore = new HashMap<>();

	private static AtomicLong seq = new AtomicLong(1000000);

	public static long generateResumeId() {
		long nextVal = seq.incrementAndGet();
		return nextVal;
	}
}
