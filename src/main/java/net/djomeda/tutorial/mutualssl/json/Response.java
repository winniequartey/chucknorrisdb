package net.djomeda.tutorial.mutualssl.json;

import java.util.List;

/**
 * Created by joseph on 9/18/16.
 */
public class Response {
    public Response(){}
    public Response(String stat, String msg, List<Element> res){
        status = stat;
        message = msg;
        response = res;
    }
    public String status;
    public String message;
    public List<Element> response;
}
