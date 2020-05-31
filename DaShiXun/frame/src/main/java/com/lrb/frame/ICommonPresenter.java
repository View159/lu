package com.lrb.frame;

public interface ICommonPresenter<P> extends ICommonView {

        void getData(int wichApi,P...pP);
}
