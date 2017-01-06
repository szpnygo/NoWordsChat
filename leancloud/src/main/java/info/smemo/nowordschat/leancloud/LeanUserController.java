package info.smemo.nowordschat.leancloud;

import android.support.annotation.NonNull;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;

public class LeanUserController extends LeanBaseController {

    public static void createUser(final String nickname, final String email, String password, @NonNull final LeanActionInterface.LeanBaseComplete complete) {
        AVUser user = new AVUser();
        user.setEmail(email);
        user.setUsername(email);
        user.setPassword(password);
        user.put("nickname", nickname);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(final AVException e) {
                if (e == null) {
                    AVObject info = new AVObject("UserInfo");
                    info.put("nickname", nickname);
                    info.put("email", email);
                    info.put("user", AVObject.createWithoutData("_User", getCurrentUser().getObjectId()));
                    info.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e0) {
                            if (e0 == null) {
                                complete.success();
                            } else {
                                error(complete, e0);
                            }
                        }
                    });
                } else {
                    error(complete, e);
                }
            }
        });

    }

    public static AVUser getCurrentUser() {
        return AVUser.getCurrentUser();
    }

}
