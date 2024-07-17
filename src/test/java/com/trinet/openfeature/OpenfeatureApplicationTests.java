package com.trinet.openfeature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.trinet.openfeature.service.OpenFeatureService;

import dev.openfeature.sdk.EvaluationContext;

@SpringBootTest(classes = OpenfeatureApplicationTests.class)
class OpenfeatureApplicationTests {
	Logger logger = LoggerFactory.getLogger(OpenfeatureApplicationTests.class);

 

	@Mock
	OpenFeatureService openFeatureService;

	@BeforeAll
	static void contextLoads() {

	}

	@Test
	void testBooleanFlagTrue() {
		logger.info("running test for boolean");
		EvaluationContext clientContext = null;
		when(openFeatureService.getBooleanDeatils("flagName", false,clientContext)).thenReturn(true);
		assertEquals(true, openFeatureService.getBooleanDeatils("flagName", false,clientContext));
	} 
	
	@Test
	void testBooleanFlagFalse() {
		logger.info("running test for boolean");
		EvaluationContext clientContext = null;
		when(openFeatureService.getBooleanDeatils("flagName", false,clientContext)).thenReturn(false);
		assertEquals(false, openFeatureService.getBooleanDeatils("flagName", false,clientContext));
	} 
}
