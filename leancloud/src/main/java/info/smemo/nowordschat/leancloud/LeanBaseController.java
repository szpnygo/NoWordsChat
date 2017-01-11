package info.smemo.nowordschat.leancloud;

import android.util.Log;

import com.avos.avoscloud.AVException;

import org.json.JSONException;
import org.json.JSONObject;

public class LeanBaseController {

    private static class ErrorMessage {
        int code = -1;
        String message = "";
    }

    protected static void error(LeanActionInterface.LeanBaseComplete complete, AVException e) {
        if (null != complete) {
            ErrorMessage errorMessage = getMessage(e.getMessage());
            complete.error(errorMessage.code, errorMessage.message);
            Log.e("LeanCloud", "code:" + errorMessage.code + " error:" + errorMessage.message);
        }
    }

    private static ErrorMessage getMessage(String data) {
        ErrorMessage errorMessage = new ErrorMessage();
        try {
            JSONObject object = new JSONObject(data);
            errorMessage.code = object.getInt("code");
            errorMessage.message = object.getString("error");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return errorMessage;
    }

}
