package anatlyzer.ide.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

public class InteractiveTransformationModel {

	@JsonProperty
	private String transformation;

	@JsonProperty
	private Map<String, String> suites = new LinkedHashMap<>();

	@JsonProperty
	private List<TestCase> testcases = new ArrayList<>();
	
	public void setTransformation(String transformation) {
		this.transformation = transformation;
	}

	@JsonIgnore
	@NonNull
	public Collection<? extends String> getSuiteFolders() {
		return suites.values();
	}
	
	public String getTransformation() {
		return transformation;
	}
	
	@NonNull
	public static InteractiveTransformationModel fromYaml(@NonNull File file) throws IOException {
		InteractiveTransformationModel model = newObjectMapper()
	      .readerFor(InteractiveTransformationModel.class)
	      .readValue(file);

		return model;
	}
	
    public static ObjectMapper newObjectMapper() {
        YAMLFactory factory = new YAMLFactory()
                .enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE)
                .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                .disable(YAMLGenerator.Feature.SPLIT_LINES);
        ObjectMapper mapper = new ObjectMapper(factory);
        // mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return mapper;
    }


    @JsonIgnore
	public @NonNull Collection<? extends TestCase> getTestCases() {
		return testcases;
	}

	
	@NonNull
	public String toYamlText() throws IOException {
		return newObjectMapper().
			writerFor(InteractiveTransformationModel.class).
			writeValueAsString(this);
	}

	@NonNull
	@JsonIgnore
	public String getTestSuiteFolder() {
		return this.suites.values().iterator().next();
	}


}
