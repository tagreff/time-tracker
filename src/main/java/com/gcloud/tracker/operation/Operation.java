package com.gcloud.tracker.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Operation {
        void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception;
        void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
