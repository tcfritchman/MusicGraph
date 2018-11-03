package com.tcfritchman;

import com.tcfritchman.parser.ReleasesParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * @author Thomas Fritchman
 */
public class Runner {

    public static void main(String[] args) {

        String releasesFile = System.getProperty("releases.file");

        ReleasesParser releasesParser = new ReleasesParser(releasesFile);

        try {
            releasesParser.parse();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
