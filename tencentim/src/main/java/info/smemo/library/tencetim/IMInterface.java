package info.smemo.library.tencetim;

import com.tencent.TIMUserProfile;

public interface IMInterface {

    interface BaseInterface{

        void error(int code,String message);
    }

    interface RegisterListener extends BaseInterface{

        void success();

    }

    interface LoginListener extends BaseInterface{

        void success();

    }

    interface SelfProfileListener extends BaseInterface{

        void success(TIMUserProfile userProfile);
    }

}
