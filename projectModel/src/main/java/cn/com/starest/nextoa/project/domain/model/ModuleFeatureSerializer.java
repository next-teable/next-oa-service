package cn.com.starest.nextoa.project.domain.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author dz
 */
public class ModuleFeatureSerializer extends JsonSerializer<Module> {

	@Override
	public void serialize(Module value, JsonGenerator generator, SerializerProvider serializers)
			throws IOException {
		generator.writeStartObject();
		generator.writeFieldName("code");
		generator.writeString(value.name());
		generator.writeFieldName("name");
		generator.writeString(value.getDisplayLabel());
		generator.writeEndObject();
	}

}
