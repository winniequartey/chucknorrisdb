package net.djomeda.tutorial.mutualssl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by joseph on 9/18/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Jokes {

    public String type;
    public List<Element> value;

}

