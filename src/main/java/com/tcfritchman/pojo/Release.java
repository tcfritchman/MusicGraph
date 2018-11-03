package com.tcfritchman.pojo;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Thomas Fritchman
 */
@Data
@XmlRootElement(name = "release")
@XmlAccessorType(XmlAccessType.FIELD)
public class Release {

    @XmlAttribute
    private String id;

    @XmlElement
    private String title;

    @XmlElementWrapper
    @XmlElement(name = "artist")
    private List<ReleaseArtist> artists;

    @XmlElementWrapper
    @XmlElement(name = "label")
    private List<ReleaseLabel> labels;

    @XmlElementWrapper(name = "extraartists")
    @XmlElement(name = "artist")
    private List<ReleaseArtist> extraArtists;

    @XmlElementWrapper
    @XmlElement(name = "genre")
    private List<String> genres;

    @XmlElementWrapper
    @XmlElement(name = "style")
    private List<String> styles;

    @XmlElement
    private String country;

    @XmlElement
    private String released;

    //TODO
    //@XmlElement
    //private String notes;

    @XmlElement(name = "data_quality")
    private String dataQuality;
}
