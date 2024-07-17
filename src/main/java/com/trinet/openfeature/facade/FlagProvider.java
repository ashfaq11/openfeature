package com.trinet.openfeature.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trinet.openfeature.service.OpenFeatureService;

import dev.openfeature.sdk.EvaluationContext;

@Component
public class FlagProvider {
	@Autowired
	OpenFeatureService openFeatureService;

	/**
	 * This method is used to retrieve the boolean variation of a specific feature
	 * flag.
	 * 
	 * @param flagName     The name of the feature flag.
	 * @param defaultValue The default value to be returned if the feature flag is
	 *                     not found or not active.
	 * @param clientCtx    The evaluation context that contains information about
	 *                     the client.
	 * 
	 * @return The boolean variation of the feature flag. If the flag is not found
	 *         or not active, the default value is returned.
	 */

	public boolean getBooleanVariation(String flagName, boolean defaultValue, EvaluationContext clientCtx) {
		return openFeatureService.getBooleanDeatils(flagName, defaultValue, clientCtx);
	}

	
//	public boolean getBooleanVariation(String flagName, boolean defaultValue) {
//		return openFeatureService.getBooleanDeatils(flagName, defaultValue);
//	}

	/**
 * This method retrieves the double variation of a specific feature flag.
 * 
 * @param flagName     The name of the feature flag.
 * @param defaultValue The default value to be returned if the feature flag is
 *                     not found or not active.
 * @param clientCtx    The evaluation context that contains information about
 *                     the client.
 * 
 * @return The double variation of the feature flag. If the flag is not found
 *         or not active, the default value is returned.
 */
	public Double getDoubleDetails(String flagName, Double defaultValue, EvaluationContext clientCtx) {
		return openFeatureService.getDoubleDetails(flagName, defaultValue, clientCtx);
	}

	/**
 * This method retrieves the integer variation of a specific feature flag.
 * 
 * @param flagName     The name of the feature flag.
 * @param defaultValue The default value to be returned if the feature flag is
 *                     not found or not active.
 * @param clientCtx    The evaluation context that contains information about
 *                     the client.
 * 
 * @return The integer variation of the feature flag. If the flag is not found
 *         or not active, the default value is returned.
 */
	public Integer getIntegerDetails(String flagName, Integer defaultValue, EvaluationContext clientCtx) {
		return openFeatureService.getIntegerDetails(flagName, defaultValue, clientCtx);
	}


	/**
 * This method retrieves the string variation of a specific feature flag.
 * 
 * @param flagName     The name of the feature flag. This parameter is a string and is required.
 * @param defaultValue The default value to be returned if the feature flag is not found or not active. 
 *                     This parameter is a string and is required.
 * @param clientCtx    The evaluation context that contains information about the client. 
 *                     This parameter is of type EvaluationContext and is required.
 * 
 * @return The string variation of the feature flag. If the flag is not found or not active, 
 *         the default value is returned. This return value is a string.
 */
	public String getStringDetails(String flagName, String defaultValue, EvaluationContext clientCtx) {
		return openFeatureService.getStringDetails(flagName, defaultValue, clientCtx);
	}

}
