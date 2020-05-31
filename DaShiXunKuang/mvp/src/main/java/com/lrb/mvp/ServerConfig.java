package com.lrb.mvp;

public   class ServerConfig {

    public static final int SERVER_TYPE=1;
    public static  String BASEURL="";


    static {
        switch (SERVER_TYPE){
            case 1:
                BASEURL="https://a.zhulong.com/openapi/";
                break;
            case 2:

                break;
            case 3:
                break;
        }
    }

}
