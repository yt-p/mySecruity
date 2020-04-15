package com.yt.exception;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/15 0015 09:22
 */
public class UserNotExistException extends RuntimeException {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserNotExistException(String id){
        super("User not exist");
        this.id = id;
    }
}
