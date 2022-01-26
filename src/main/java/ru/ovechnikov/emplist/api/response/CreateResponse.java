package ru.ovechnikov.emplist.api.response;

public class CreateResponse extends ResultResponse {

    private Integer createdId;

    public CreateResponse(boolean result, int createdId) {
        super(result);
        this.createdId = createdId;
    }


    public Integer getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Integer createdId) {
        this.createdId = createdId;
    }
}
