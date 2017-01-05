package info.smemo.nowordschat.base;

public interface BaseActionInterface {

    interface BaseComplete {

        void success();

        void error(int code, String message);
    }
}
