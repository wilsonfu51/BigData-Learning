package com.wilson.java.Lock;

public class LockMap {

    private Long lockId;
    private Boolean lockGeted;

    public LockMap(long l, boolean b) {
        lockId = l;
        lockGeted = b;
    }

    public void setLockId(Long l) {
        lockId = l;
    }

    public Long getLockId(){
        return lockId;
    }

    public void setLockGeted(Boolean b) {
        lockGeted = b;
    }

    public Boolean getLockGeted(){
        return lockGeted;
    }

}
