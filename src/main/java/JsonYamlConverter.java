import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import java.io.IOException;
public class JsonYamlConverter {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final YAMLMapper YAML_MAPPER = new YAMLMapper(
            new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
    );
    public static ConversionResult convert(File inputFile, File outputDir) {
        try {
            long startTime = System.currentTimeMillis();

            File outputFile;
            String newExtension;

            if (inputFile.getName().endsWith(".json")) {
                JsonNode jsonNode = JSON_MAPPER.readTree(inputFile);
                outputFile = new File(outputDir, inputFile.getName().replace(".json", ".yaml"));
                YAML_MAPPER.writeValue(outputFile, jsonNode);
                newExtension = "yaml";
            } else {
                Object jsonObject = YAML_MAPPER.readValue(inputFile, Object.class);
                outputFile = new File(outputDir, inputFile.getName().replace(".yaml", ".json"));
                JSON_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
                JSON_MAPPER.writeValue(outputFile, jsonObject);
                newExtension = "json";
            }

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            long oldSize = FileUtilsWorker.getFileSize(inputFile);
            long newSize = FileUtilsWorker.getFileSize(outputFile);

            boolean error = false;
            System.out.printf("Done");

            return new ConversionResult(inputFile.getName(), outputFile.getName(), duration, oldSize, newSize, error);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to convert file: " + inputFile.getName());
            boolean error = true;
            System.out.printf("Error");
            return new ConversionResult(inputFile.getName(), "", 0, 0, 0, error);
        }
    }
}