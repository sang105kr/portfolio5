package com.kh.portfolio.aspect;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // 컨테이너에 빈으로 등록하고자 하는 클래스에 사용
@Aspect // 부가기능을 정의한 클래스에 사용
public class MeasuringExcuteTime {
	private static final Logger logger = LoggerFactory.getLogger(MeasuringExcuteTime.class);

	@Around("execution(* com.kh.portfolio..*.*(..))")
	public Object measuringMethodRoundingTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		Signature signature = joinPoint.getSignature();
		StringBuilder methodName = new StringBuilder();
		methodName.append(signature.getDeclaringTypeName()).append(".").append(signature.getName());

		logger.info("[Log: Around]Before:" + methodName + " start : " + Arrays.toString(joinPoint.getArgs()));
		long startTime = System.nanoTime(); // 시작시간

		try {
			// 핵심기능
			result = joinPoint.proceed();
		} catch (Throwable e) {
			logger.info("[Log: Arround] Exception: " + methodName);
			throw e;
		} finally {
			logger.info("[Log: Arround] finally: " + methodName);
			// 핵심기능이 정상적으로 수행된경우
			if (result != null) {
				logger.info("Log: Arround] result: " + result.toString());
			}
		}

		long endTime = System.nanoTime(); // 종료시간
//		long takenTime = TimeUnit.NANOSECONDS.toSeconds(endTime-startTime);
		double takenTime = (endTime - startTime) / (double) 1000000000;
		logger.info("[Log: Around]After:" + methodName + " end");
		logger.info("[Log: Around] : " + methodName + " Processing time : " + takenTime + " sec");
		return result;
	}
}
