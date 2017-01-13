package info.smemo.nowordschat.presenter;


import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.action.UserInfoAction;
import info.smemo.nowordschat.appaction.ActionInterface;
import info.smemo.nowordschat.contract.CommonEditContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditNickPresenter implements CommonEditContract.Presenter {

    private CommonEditContract.View mView;

    @Override
    public void start() {

    }

    @Override
    public void init(CommonEditContract.View view) {
        mView = checkNotNull(view, "FindContract.View cannot be null");
        mView.setPresenter(this);
        mView.setTitle("更改名字");
        mView.setEditBean(UserInfoAction.getUserInfo().nickname);
        mView.setCounterMaxLength(20);
        mView.setOnDoneListener(new CommonEditContract.EditDoneListener() {
            @Override
            public void done() {
                editDone();
            }
        });
    }

    @Override
    public void editDone() {
        if (StringUtil.isEmpty(mView.getText())) {
            mView.setMessageError("昵称不能为空");
            return;
        }
        if (mView.getText().length() < 2 || mView.getText().length() > 20) {
            mView.setMessageError("昵称请在2-20字节之间");
            return;
        }
        if (mView.getText().equals(UserInfoAction.getUserInfo().nickname)) {
            mView.finishSelf();
            return;
        }
        updateNickname();
    }

    @Override
    public void updateNickname() {
        UserInfoAction.setNickname(mView.getText(), new ActionInterface.BaseComplete() {
            @Override
            public void success() {
                UserInfoAction.getUserInfo().setNickname(mView.getText());
                mView.finishSelf();
            }

            @Override
            public void error(int code, String message) {
                mView.setMessageError(message);
            }
        });

    }
}
