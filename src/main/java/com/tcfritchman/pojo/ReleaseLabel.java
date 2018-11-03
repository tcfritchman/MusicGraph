package com.tcfritchman.pojo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Thomas Fritchman
 */
@Data
@XmlRootElement(name = "label")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReleaseLabel {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String catno;

    @XmlAttribute
    private String name;
}
