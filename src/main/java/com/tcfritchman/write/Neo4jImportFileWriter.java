package com.tcfritchman.write;

import lombok.Getter;
import lombok.NonNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Thomas Fritchman
 */
public abstract class Neo4jImportFileWriter<T> implements Closeable, Consumer<T> {

    private static final String DELIMITER = ",";

    @NonNull
    @Getter
    private String title;

    @NonNull
    @Getter
    private String[] headers;

    @NonNull
    private Function<T, String[]> transformer;

    @Getter
    private String headerFileName;

    @Getter
    private String dataFileName;

    private BufferedWriter dataWriter;

    Neo4jImportFileWriter(String title, String[] headers, Function<T, String[]> transformer) {
        this.title = title;
        this.headers = headers;
        this.headerFileName = title + "-header.csv";
        this.dataFileName = title + ".csv";
        this.transformer = transformer;
    }

    private void openDataFile() throws IOException {
        dataWriter = new BufferedWriter(new FileWriter(dataFileName));
    }

    private void writeHeaderFile() throws IOException {
        Path file = Paths.get(headerFileName);

        String heading = String.join(DELIMITER, headers);

        Files.write(file, Arrays.asList(heading), StandardCharsets.UTF_8,
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    }

    void write(T t) {
        if (dataWriter == null) {
            try {
                writeHeaderFile();
                openDataFile();
            } catch (IOException e) {
                throw new Neo4jFileWriteException("Failed to write header file: " + headerFileName, e);
            }
        }

        String[] fields = transformer.apply(t);
        String line = String.join(DELIMITER, fields);

        try {
            dataWriter.write(line);
            dataWriter.newLine();
        } catch (IOException e) {
            throw new Neo4jFileWriteException("Failed to write data file: " + dataFileName, e);
        }
    }

    @Override
    public void close() throws IOException {
        dataWriter.close();
    }
}
