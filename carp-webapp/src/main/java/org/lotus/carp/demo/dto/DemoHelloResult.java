package org.lotus.carp.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.lotus.carp.base.jackson.DateJsonSerializer;
import org.lotus.carp.base.jackson.DateTimeJsonSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 5/17/2018
 * Time: 10:27 AM
 */
@Data
public class DemoHelloResult implements Serializable {
    private static final long serialVersionUID = 4731356035043416289L;
    private String who;
    private String messge;
    @JsonSerialize(using = DateJsonSerializer.class)
    private Date time;
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    private Date fullTime;
}
