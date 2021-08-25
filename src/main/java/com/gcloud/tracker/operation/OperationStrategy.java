package com.gcloud.tracker.operation;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class OperationStrategy {

    static Map<String, Operation> operationMap = new HashMap<>();

    static {
        operationMap.put("/", new LoginOperation());
        operationMap.put("/mainPage", new MainOperation());
        operationMap.put("/createUser", new CreateUserOperation());
        operationMap.put("/changeTask", new ChangeTaskOperation());
        operationMap.put("/deleteTask", new DeleteTaskOperation());
        operationMap.put("/sendReport", new SendReportOperation());
    }

    public static Operation getOperation(HttpServletRequest request) {
        return operationMap.get(request.getServletPath());
    }
}
