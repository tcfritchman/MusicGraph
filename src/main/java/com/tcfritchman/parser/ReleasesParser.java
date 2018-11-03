package com.tcfritchman.parser;

import com.tcfritchman.pojo.Release;
import com.tcfritchman.pojo.Releases;
import com.tcfritchman.write.ReleaseNodeWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Thomas Fritchman
 */
public class ReleasesParser {

    private final String file;

    public ReleasesParser(String file) {
        this.file = file;
    }

    public void parse() throws JAXBException, IOException {

        try (
                final InputStream stream = new FileInputStream(file);
                final ReleaseNodeWriter releaseNodeWriter = new ReleaseNodeWriter();
                PartialUnmarshaller<Release> unmarshaller = new PartialUnmarshaller<>(stream, Release.class);
        ) {

            while (unmarshaller.hasNext()) {
                Release release = unmarshaller.next();
                releaseNodeWriter.accept(release);
            }

        } catch (XMLStreamException e) {
            //todo
            e.printStackTrace();
        }
    }
}
