package com.ryndrappf.uploadcsv.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {

    private boolean status;

    private List<String> messages = new ArrayList<>();

    private Object payLoad;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Object getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(Object payLoad) {
        this.payLoad = payLoad;
    }
}
