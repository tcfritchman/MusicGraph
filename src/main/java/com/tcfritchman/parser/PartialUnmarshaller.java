package com.tcfritchman.parser;

import lombok.NonNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.Closeable;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static javax.xml.stream.XMLStreamConstants.*;

/**
 * @author Thomas Fritchman
 */
public class PartialUnmarshaller<T> implements Closeable {

    private XMLStreamReader reader;
    private Class<T> clazz;
    private Unmarshaller unmarshaller;

    PartialUnmarshaller(@NonNull InputStream stream, @NonNull Class<T> clazz)
            throws XMLStreamException, FactoryConfigurationError, JAXBException {

        this.clazz = clazz;
        this.unmarshaller = JAXBContext.newInstance(clazz).createUnmarshaller();
        this.reader = XMLInputFactory.newInstance().createXMLStreamReader(stream);

        skipElements(START_DOCUMENT, DTD);

        reader.nextTag();

        skipElements(END_ELEMENT);
    }

    T next() throws XMLStreamException, JAXBException {
        if (!hasNext())
            throw new NoSuchElementException();

        T value = unmarshaller.unmarshal(reader, clazz).getValue();

        skipElements(CHARACTERS, END_ELEMENT);
        return value;
    }

    boolean hasNext() throws XMLStreamException {
        return reader.hasNext();
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch (XMLStreamException e) {
            //todo
            e.printStackTrace();
        }
    }

    private void skipElements(int... elements) throws XMLStreamException {
        int eventType = reader.getEventType();

        List<Integer> types = IntStream.of(elements).boxed().collect(Collectors.toList());

        while (types.contains(eventType)) {
            eventType = reader.next();
        }
    }
}

/*
 * PartialUnmarshaller borrowed from https://stackoverflow.com/a/9260039/2744634
 */
