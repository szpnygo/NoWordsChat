package info.smemo.nowordschat.appaction.enums;

public enum IMFutureFriendType {

    IM_FUTURE_FRIEND_PENDENCY_IN_TYPE(1),
    IM_FUTURE_FRIEND_PENDENCY_OUT_TYPE(2),
    IM_FUTURE_FRIEND_RECOMMEND_TYPE(4),
    IM_FUTURE_FRIEND_DECIDE_TYPE(8);

    private int value;

    public final int getValue() {
        return this.value;
    }

    private IMFutureFriendType(int var3) {
        this.value = var3;
    }

}
