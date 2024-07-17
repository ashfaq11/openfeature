package com.trinet.openfeature.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trinet.openfeature.facade.FlagProvider;
import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.ImmutableContext;
import dev.openfeature.sdk.Value;

@RestController
public class OpenfeatureController {

	@Autowired
	FlagProvider flagProvider;

	@GetMapping("/flag/bool-variation")
	public boolean booleanVariation(@RequestParam String flagName,@RequestParam boolean defaultValue) throws InstantiationException {
		Map<String, Value> map = new HashMap<>();
		map.put("name", new Value(flagName));
		map.put("identifier", new Value(flagName));
		map.put("region", new Value("uk"));
		
		EvaluationContext clientCtx = new ImmutableContext(map);
		
 		return flagProvider.getBooleanVariation(flagName, defaultValue, clientCtx);

	}

	
	@GetMapping("/flag/Double-variation")
	public Double doubleVariation( @RequestParam String flagName, @RequestParam Double defaultValue) {
		Map<String, Value> requestAttrs = new HashMap<>();
		requestAttrs.put("name", new Value(flagName));
		requestAttrs.put("identifier", new Value(flagName));
		EvaluationContext clientCtx = new ImmutableContext(requestAttrs);
		return flagProvider.getDoubleDetails(flagName, defaultValue, clientCtx);
	}
	
	@GetMapping("/flag/Integer-variation")
	public Integer integerVariation( @RequestParam String flagName, @RequestParam Integer defaultValue) {
		Map<String, Value> requestAttrs = new HashMap<>();
		requestAttrs.put("name", new Value(flagName));
		requestAttrs.put("identifier", new Value(flagName));
		EvaluationContext clientCtx = new ImmutableContext(requestAttrs);
		return flagProvider.getIntegerDetails(flagName, defaultValue, clientCtx);
	}
	
	@GetMapping("/flag/String-variation")
	public String stringVariation( @RequestParam String flagName, @RequestParam String defaultValue) {
		Map<String, Value> requestAttrs = new HashMap<>();
		requestAttrs.put("name", new Value(flagName));
		requestAttrs.put("identifier", new Value(flagName));
		EvaluationContext clientCtx = new ImmutableContext(requestAttrs);
		return flagProvider.getStringDetails(flagName, defaultValue, clientCtx);
	}
}
