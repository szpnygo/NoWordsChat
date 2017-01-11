package info.smemo.nowordschat.appaction;

public interface ActionInterface {

    interface BaseComplete {

        void success();

        void error(int code, String message);
    }
}
