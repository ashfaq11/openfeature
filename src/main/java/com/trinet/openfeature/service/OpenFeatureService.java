package com.trinet.openfeature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trinet.openfeature.utils.FeatureFlagConstants;

import dev.openfeature.contrib.providers.flagd.FlagdProvider;
import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.OpenFeatureAPI;
import dev.openfeature.sdk.Value;

@Component
public class OpenFeatureService {
	
	

	@Autowired
	FlagdProvider flagdProvider;

	public boolean getBooleanDeatils(String flagName, boolean defaultValue,EvaluationContext clientCtx) {
		OpenFeatureAPI api = OpenFeatureAPI.getInstance();
		api.setProvider(flagdProvider);
		api.setEvaluationContext(clientCtx);
		Client client = api.getClient(FeatureFlagConstants.CLIENT_NAME);
		return client.getBooleanDetails(flagName, defaultValue,clientCtx).getValue();
	}
	
	

	public Integer getIntegerDetails(String flagName, Integer defaultValue, EvaluationContext clientCtx) {
		OpenFeatureAPI api = OpenFeatureAPI.getInstance();
		api.setProvider(flagdProvider);
		api.setEvaluationContext(clientCtx);
		Client client = api.getClient(FeatureFlagConstants.CLIENT_NAME);
		return client.getIntegerDetails(flagName, defaultValue, clientCtx).getValue();
	}


	public Double getDoubleDetails(String flagName, Double defaultValue, EvaluationContext clientCtx) {
		OpenFeatureAPI api = OpenFeatureAPI.getInstance();
		api.setProvider(flagdProvider);
		api.setEvaluationContext(clientCtx);
		Client client = api.getClient(FeatureFlagConstants.CLIENT_NAME);
		return client.getDoubleDetails(flagName, defaultValue, clientCtx).getValue();
	}

	public String getStringDetails(String flagName, String defaultValue, EvaluationContext clientCtx) {
		OpenFeatureAPI api = OpenFeatureAPI.getInstance();
		api.setProvider(flagdProvider);
		api.setEvaluationContext(clientCtx);
		Client client = api.getClient(FeatureFlagConstants.CLIENT_NAME);
		return client.getStringDetails(flagName, defaultValue, clientCtx).getValue();
	}

	public Object getObjectDetails(String flagName, Value defaValue, EvaluationContext clientCtx) {
		OpenFeatureAPI api = OpenFeatureAPI.getInstance();
		api.setProvider(flagdProvider);
		api.setEvaluationContext(clientCtx);
		Client client = api.getClient(FeatureFlagConstants.CLIENT_NAME);
		return client.getObjectDetails(flagName, defaValue, clientCtx).getValue();
	}
}
