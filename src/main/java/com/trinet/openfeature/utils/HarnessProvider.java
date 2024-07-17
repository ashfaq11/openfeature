package com.trinet.openfeature.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.FeatureProvider;
import dev.openfeature.sdk.Metadata;
import dev.openfeature.sdk.ProviderEvaluation;
import dev.openfeature.sdk.ProviderState;
import dev.openfeature.sdk.Value;
import io.harness.cf.client.api.BaseConfig;
import io.harness.cf.client.api.CfClient;
import io.harness.cf.client.api.FeatureFlagInitializeException;
import io.harness.cf.client.connector.HarnessConfig;
import io.harness.cf.client.connector.HarnessConnector;
import io.harness.cf.client.dto.Target;

public class HarnessProvider implements FeatureProvider {

	CfClient cfClient;

	private static final String IDENTIFIER = "identifier";
	private static final String NAME = "name";

	@Override
	public Metadata getMetadata() {
		return null;
	}

	public HarnessProvider() {
//		HarnessConfig connectorConfig = HarnessConfig.builder().configUrl("https://config.ff.harness.io/api/1.0")
//				.eventUrl("https://events.ff.harness.io/api/1.0").build();
//
//		BaseConfig options = BaseConfig.builder().pollIntervalInSeconds(10).streamEnabled(true).analyticsEnabled(true)
//				.build();
//
//		// Create the client
//		cfClient = new CfClient(new HarnessConnector(apiKey, connectorConfig), options);
//		try {
//			cfClient.waitForInitialization();
//		} catch (InterruptedException | FeatureFlagInitializeException e) {
//			e.printStackTrace();
//			throw new IllegalArgumentException(e.getMessage());
//		}
	}

	@Override
	public void initialize(EvaluationContext evaluationContext) {

	}

	private Target createTarget(EvaluationContext ctx) {
		Target target = null;
		String identifier = null;
		String name = null;

		if (ctx != null && ctx.getValue(IDENTIFIER) != null
				&& !(ctx.getValue(IDENTIFIER).asString().isEmpty())) {
			identifier = ctx.getValue(IDENTIFIER).asString();
			name = ctx.getValue(NAME) != null ? ctx.getValue(NAME).asString() : identifier;
			target = Target.builder().identifier(identifier).name(name).build();
		}

		return target;
	}

	@Override
	public ProviderEvaluation<Boolean> getBooleanEvaluation(String flagName, Boolean defaultValue, EvaluationContext ctx) {

		return ProviderEvaluation.<Boolean>builder().value(cfClient.boolVariation(flagName, createTarget(ctx), false))
				.build();
	}

	@Override
	public ProviderEvaluation<String> getStringEvaluation(String flagName, String defaultValue, EvaluationContext ctx) {
		return ProviderEvaluation.<String>builder()
				.value(cfClient.stringVariation(flagName, createTarget(ctx), defaultValue)).build();
	}

	@Override
	public ProviderEvaluation<Integer> getIntegerEvaluation(String flagName, Integer defaultValue, EvaluationContext ctx) {

		return ProviderEvaluation.<Integer>builder()
				.value((int) cfClient.numberVariation(flagName, createTarget(ctx), defaultValue)).build();
	}

	@Override
	public ProviderState getState() {
		return ProviderState.READY;
	}

	@Override
	public ProviderEvaluation<Double> getDoubleEvaluation(String flagName, Double defaultValue, EvaluationContext ctx) {

		return ProviderEvaluation.<Double>builder()
				.value(cfClient.numberVariation(flagName, createTarget(ctx), defaultValue)).build();
	}

	@Override
	public ProviderEvaluation<Value> getObjectEvaluation(String flagName, Value defaultValue, EvaluationContext ctx) {

		JsonObject jsonObject = JsonParser.parseString(defaultValue.asString()).getAsJsonObject();
		JsonObject response = cfClient.jsonVariation(flagName, createTarget(ctx), jsonObject);

		try {
			return ProviderEvaluation.<Value>builder().value(new Value(response)).build();
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

}
