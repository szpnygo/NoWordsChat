package info.smemo.nowordschat.presenter;

import info.smemo.nowordschat.action.UserInfoAction;
import info.smemo.nowordschat.appaction.ActionInterface;
import info.smemo.nowordschat.contract.CommonEditContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditSignaturePresenter implements CommonEditContract.Presenter {

    private CommonEditContract.View mView;

    @Override
    public void init(CommonEditContract.View view) {
        mView = checkNotNull(view, "FindContract.View cannot be null");
        mView.setPresenter(this);
        mView.setTitle("个性签名");
        mView.setEditBean(UserInfoAction.getUserInfo().selfSignature);
        mView.setCounterMaxLength(30);
        mView.setOnDoneListener(new CommonEditContract.EditDoneListener() {
            @Override
            public void done() {
                editDone();
            }
        });
    }

    @Override
    public void editDone() {
        if (mView.getText().length() > 30) {
            mView.setMessageError("个性签名请勿超过30个字符");
            return;
        }
        if (mView.getText().equals(UserInfoAction.getUserInfo().selfSignature)) {
            mView.finishSelf();
            return;
        }
        updateText();
    }

    @Override
    public void updateText() {
        UserInfoAction.setSignature(mView.getText(), new ActionInterface.BaseComplete() {
            @Override
            public void success() {
                UserInfoAction.getUserInfo().setSelfSignature(mView.getText());
                mView.finishSelf();
            }

            @Override
            public void error(int code, String message) {
                mView.setMessageError(message);
            }
        });
    }

    @Override
    public void start() {

    }
}
