package com.gcloud.tracker.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception;
    void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
