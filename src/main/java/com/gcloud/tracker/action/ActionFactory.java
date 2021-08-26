package com.gcloud.tracker.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    static Map<String, Action> actions = new HashMap();

    static {
        actions.put("/changeTask", new ChangeTaskAction());
        actions.put("/createUser", new CreateUserAction());
        actions.put("/deleteTask", new DeleteTaskAction());
        actions.put("/", new LoginAction());
        actions.put("/mainPage", new MainAction());
        actions.put("/sendReport", new SendReportAction());
    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getServletPath());
    }
}