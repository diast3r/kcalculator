package com.kcalculator.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestTask {
	
	// Job 생성
	@Scheduled(cron = "0 */1 * * * *") // crontab에서 따와서 cron. 이 값으로 Job이 수행될 주기를 정해줌. crontab 시간설정 검색
	public void testJob() {
		log.info("########### job 수행");
	}
}
