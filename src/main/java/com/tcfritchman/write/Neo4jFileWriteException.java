package com.tcfritchman.write;

/**
 * @author Thomas Fritchman
 */
public class Neo4jFileWriteException extends RuntimeException {

    public Neo4jFileWriteException(String message) {
        super(message);
    }

    public Neo4jFileWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
