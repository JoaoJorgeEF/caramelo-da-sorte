package br.edu.ifpb.pweb2.caramelodasorte.service.observer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public class EventManager {

    public HashMap<String, HashMap<String, EventListener>> listeners = new HashMap<>();

    public EventManager() {
        ArrayList<String> operations = new ArrayList<>();
        operations.add("email");
        for (String operation: operations) {
            this.listeners.put(operation, new HashMap<>());
        }
    }

    public void subscribe(String eventType, String email, EventListener listener) {
        HashMap<String, EventListener> users = listeners.get(eventType);
        users.put(email,listener);
    }

    public void notify(String eventType) {
        HashMap<String, EventListener> users = listeners.get(eventType);
        for (Map.Entry<String, EventListener> entry : users.entrySet()) {
            entry.getValue().update(entry.getKey());
        }
    }
}
