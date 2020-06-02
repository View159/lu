package com.lrb.infobean;

import java.io.Serializable;

public   class FirstBean<D> implements Serializable {

    private static final long serialVersionUID = 5426691696275532617L;
    public int errNo;
    public int exeTime;
    public String msg;
    public D result;

    public boolean isSuccess() {
        return errNo == 0;
    }
}

