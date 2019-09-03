package com.bawie.contract;

import java.io.IOException;

public interface IHomeContract {
    //view interface
    interface Iview{
        //成功
        void onHomeSuccess();
        //失败
        void onHomeFailure(Throwable e);
    }
    //model interface
    interface Imodel{
        void getHomeData(String path,IModelCallback iModelCallback)throws IOException;
        interface IModelCallback{
            //成功
            void onHomeSuccess();
            //失败
            void onHomeFailure(Throwable e);
        }
    }
    //presenter interface
    interface Ipresenter{
        //绑定
        void attachView();
        //解除绑定
        void detachView();
        //初始化
        void getModel();
    }
}
