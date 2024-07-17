OpenFeature Implementation using Harness provider:
1.	Overview:
o	Created a Spring Boot application to demonstrate the use of Harness provider using OpenFeature evaluation API.
2.	Evaluation API:
o	The Evaluation API is the primary component of OpenFeature that application authors interact with. The Evaluation API allows developers to evaluate feature flags to alter control flow and application characteristics.
import dev.openfeature.javasdk.OpenFeatureAPI;

OpenFeatureAPI api = OpenFeatureAPI.getInstance();
api.setProvider(new YourProviderOfChoice()); // harness provider now implemented in code
3.	Evaluation Context:
In server-side SDKs, values relevant for flag evaluation can be included in the evaluation context at multiple points: globally (on the top level API), on the client, and at the point of flag evaluation (invocation).


// add a value to the global context
OpenFeatureAPI api = OpenFeatureAPI.getInstance();
api.setEvaluationContext(new MutableContext().add("myGlobalKey", "myGlobalValue"));

// add a value to the client context
Client client = api.getClient();
client.setEvaluationContext(new MutableContext().add("myClientKey", "myClientValue"));

// add a value to the invocation context
EvaluationContext context = new MutableContext();
context.addStringAttribute("myInvocationKey", "myInvocationValue")
Boolean boolValue = client.getBooleanValue("boolFlag", false, context);


3.	Implementation:
o	Used evaluation API from OpenFeature and have created a Harness provider and passed Harness provider to evaluation API.
o	Using existing OpenFeatureAPI methods, internally we are calling Harness ClientAPI.
4.	Library Creation:
o	Creating a library to fetch feature flag values through OpenFeature API methods for the following data types:
	Boolean
	String
	Integer
	Double
	Json
5.	Library Methods:
o	getBooleanDetails(String flagName, boolean defaultValue, EvaluationContext clientCtx)
o	getIntegerDetails(String flagName, Integer defaultValue, EvaluationContext clientCtx)
o	getDoubleDetails(String flagName, Double defaultValue, EvaluationContext clientCtx)
o	getStringDetails(String flagName, String defaultValue, EvaluationContext clientCtx)
o	getObjectDetails(String flagName, Value defaValue, EvaluationContext clientCtx)
Clients can import this library and use the above methods. Internally, it will connect to Harness and fetch flag values.
6.	Controller Endpoints:
	To demonstrate it, provided controller with the following endpoints:
http://localhost:8081/flag/bool-variation?flagName=testFlag&defaultValue=false
http://localhost:8081/flag/Integer-variation?flagName=intFlag&defaultValue=0
http://localhost:8081/flag/Double-variation?flagName=doubleFlag&defaultValue=0
http://localhost:8081/flag/String-variation?flagName=stringFlag&defaultValue=default

testcases: 
test cases has written for boolean flag

pending:
testcases for string, number, Double, Json variation.
implementation json flag.


How to use application:
 1. build the application using command
 	mvn install
 2. create jar file openfeature-0.0.1-SNAPSHOT.jar
 3. run the application using
 	java -jar openfeature-0.0.1-SNAPSHOT.jar
 4. use the below endpoint to test the application
	http://localhost:8081/flag/bool-variation?flagName=testFlag&defaultValue=false
	http://localhost:8081/flag/Integer-variation?flagName=intFlag&defaultValue=0
	http://localhost:8081/flag/Double-variation?flagName=doubleFlag&defaultValue=0
	http://localhost:8081/flag/String-variation?flagName=stringFlag&defaultValue=default