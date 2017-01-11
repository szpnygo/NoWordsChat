package info.smemo.nowordschat.appaction.enums;

public enum IMFriendGenderType {
    Unknow(0L),
    Male(1L),
    Female(2L);

    private long gender = 0L;

    private IMFriendGenderType(long gender) {
        this.gender = gender;
    }

    public final long getValue() {
        return this.gender;
    }
}
