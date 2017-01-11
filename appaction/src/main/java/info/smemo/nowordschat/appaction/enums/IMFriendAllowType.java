package info.smemo.nowordschat.appaction.enums;

public enum IMFriendAllowType {

    IM_FRIEND_INVALID("AllowType_Type_Invalid"),
    IM_FRIEND_ALLOW_ANY("AllowType_Type_AllowAny"),
    IM_FRIEND_DENY_ANY("AllowType_Type_DenyAny"),
    IM_FRIEND_NEED_CONFIRM("AllowType_Type_NeedConfirm");

    private String type = "";

    private IMFriendAllowType(String type) {
        this.type = type;
    }

    final String getType() {
        return this.type;
    }
}
