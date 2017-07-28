package beans.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "rate")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rate")
public enum Rate {
    HIGH, MID, LOW
}
