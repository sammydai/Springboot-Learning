package com.learning.helloworld.serializabile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * @Package: com.learning.helloworld.serializabile
 * @Description: CustomCarDeserializer
 * @Author: Sammy
 * @Date: 2020/12/14 15:07
 */

public class CustomCarDeserializer extends StdDeserializer<Car> {

	public CustomCarDeserializer() {
		this(null);
	}

	public CustomCarDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Car deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		Car car = new Car();
		ObjectCodec codec = jsonParser.getCodec();
		JsonNode node = codec.readTree(jsonParser);

		JsonNode colorNode = node.get("color");
		String color = colorNode.asText();
		car.setColor(color);
		return car;
	}
}
