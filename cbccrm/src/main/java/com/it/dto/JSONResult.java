package com.it.dto;

/**
 * Created by xieyue on 2016/7/11.
 * JSONResult
 */


import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JSONResult {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String state;
    private String message;
    private Object data;



    public JSONResult( Object data) {
        this(SUCCESS,data);
    }

    public JSONResult( String message) {
        this(ERROR,message);
    }


    public JSONResult(String state, Object data) {
        this.state = state;
        this.data = data;
    }

    public JSONResult(String state, String message) {
        this.state = state;
        this.message = message;
    }
}
