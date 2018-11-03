package com.tcfritchman.pojo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Thomas Fritchman
 */
@Data
@XmlRootElement(name = "artist")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReleaseArtist {

    @XmlElement
    private String id;

    @XmlElement
    private String name;

    @XmlElement
    private String anv;

    @XmlElement
    private String role;
}
