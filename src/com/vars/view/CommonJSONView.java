package com.vars.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonJSONView extends AbstractView {

    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PrintWriter printWriter = response.getWriter();

        // check if there is an error
        Error error = (Error) map.get("error");
        Object result = map.get("result");
        String callback = (String) map.get("callback");

        response.setContentType("application/json");
        if (callback != null && !callback.equalsIgnoreCase("")) {
            printWriter.write(callback + "(");
        }
        if (error != null) {
            gson.toJson(error, printWriter);
        } else {
            gson.toJson(result, printWriter);
        }
        if (callback != null && !callback.equalsIgnoreCase("")) {
            printWriter.write(")");
        }
        printWriter.flush();
    }
}
