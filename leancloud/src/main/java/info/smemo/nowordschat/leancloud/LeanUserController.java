package info.smemo.nowordschat.leancloud;

import android.support.annotation.NonNull;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

public class LeanUserController {

    public static void createUser(String nickname, String username, String password, String email, int sex, @NonNull final LeanActionInterface.LeanBaseComplete complete) {
        AVUser user = new AVUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.put("sex", sex);
        user.put("nickname", nickname);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    complete.success();
                } else {
                    complete.error(e.getCode(), e.getMessage());
                }
            }
        });
    }

    public static AVUser getCurrentUser() {
        return AVUser.getCurrentUser();
    }

}
