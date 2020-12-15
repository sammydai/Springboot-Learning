package com.learning.helloworld.serializabile;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @Package: com.learning.helloworld.serializabile
 * @Description: CustomCarSerializer 设置自定义的序列化转换器
 * @Author: Sammy
 * @Date: 2020/12/14 15:02
 */

public class CustomCarSerializer extends StdSerializer<Car> {

	public CustomCarSerializer() {
		this(null);
	}

	public CustomCarSerializer(Class<Car> t) {
		super(t);
	}

	@Override
	public void serialize(Car car, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("car_brand", car.getType());
		jsonGenerator.writeEndObject();
	}
}
