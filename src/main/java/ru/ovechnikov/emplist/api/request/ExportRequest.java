package ru.ovechnikov.emplist.api.request;

import java.util.List;

public class ExportRequest {

    private List<String> head;
    private List<List<String>> body;


    public List<String> getHead() {
        return head;
    }

    public void setHead(List<String> head) {
        this.head = head;
    }

    public List<List<String>> getBody() {
        return body;
    }

    public void setBody(List<List<String>> body) {
        this.body = body;
    }
}
