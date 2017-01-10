package info.smemo.library.tencetim;

public interface IMInterface {

    interface BaseInterface{

        void error(int code,String message);
    }

    interface RegisterListener extends BaseInterface{

        void success();

    }

}
