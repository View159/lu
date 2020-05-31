package com.lrb.frame;

public interface ICommonView<V> {

    void Success(int whichApi,int loadType, V ... view);
    void Falied(int whichApi, Throwable throwable);
}
