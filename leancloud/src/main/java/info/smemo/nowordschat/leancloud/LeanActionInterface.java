package info.smemo.nowordschat.leancloud;

public interface LeanActionInterface {

    interface LeanBaseComplete {

        void success();

        void error(int code, String message);
    }

}
