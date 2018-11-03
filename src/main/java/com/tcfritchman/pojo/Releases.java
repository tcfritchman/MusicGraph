package com.tcfritchman.pojo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Thomas Fritchman
 */
@Data
@XmlRootElement(name = "releases")
@XmlAccessorType(XmlAccessType.FIELD)
public class Releases {

    @XmlElement(name="release")
    private List<Release> releases;
}
