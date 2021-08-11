package com.example.tier3.network;

import java.util.HashMap;
import java.util.Map;

public class RequestDispatcher {

    private Map<String, Controller> mappings = new HashMap<>();

    public void register(String addr, Controller c) {
        mappings.put(addr, c);
    }

    public Response handle(Request req) {
        return mappings.get(req.getAddress())
                .handle(req);
    }
}
