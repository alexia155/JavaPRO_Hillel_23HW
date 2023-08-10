import java.io.File;
public class App {
    public static void main(String[] args) {
        String inputDirPath = args.length > 0 ? args[0] : ".";
        File inputDir = new File(inputDirPath);

        File outputDir = new File(inputDir, "converted");
        outputDir.mkdirs();

        File[] files = inputDir.listFiles();
        if (files != null) {
            StringBuilder logBuilder = new StringBuilder();

            for (File file : files) {
                if (file.isFile() && (file.getName().endsWith(".json") || file.getName().endsWith(".yaml"))) {
                    ConversionResult result = JsonYamlConverter.convert(file, outputDir);

                    if (!result.isError()) {
                        logBuilder.append(result.getOldFileName()).append(" -> ").append(result.getNewFileName())
                                .append(", ").append(result.getDuration()).append("ms, ")
                                .append(result.getOldSize()).append(" bytes -> ").append(result.getNewSize()).append(" bytes")
                                .append("\n");
                    }
                    else {
                        logBuilder.append(result.getOldFileName()).append(" -> ").append(result.getNewFileName())
                                .append(", error -> Can't parse file").append("\n");
                    }
                }
            }

            FileUtilsWorker.writeToFile(new File("result.log"), logBuilder.toString());
        }
    }
}