package com.lrb.frame;

public interface ICommonModule<M> {

    void getData(ICommonPresenter presenter,int wichApi,M...pM);
}
