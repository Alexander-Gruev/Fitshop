package com.example.fitshop.web;

public class ObjectNotFoundException extends RuntimeException{

    private Long objectId;

    public ObjectNotFoundException(Long objectId) {
        super("Object with id " + objectId + "does not exist");
        this.objectId = objectId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public ObjectNotFoundException setObjectId(Long objectId) {
        this.objectId = objectId;
        return this;
    }
}
