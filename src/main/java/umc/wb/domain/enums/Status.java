package umc.wb.domain.enums;

public enum Status {
    PENDING(0), COMPLETED(1), EXPIRED(3), IN_PROGRESS(2);

    private final Integer code;

    Status(Integer code) {
        this.code = code;
    }

    public static Status fromCode(Integer code) {
        return switch (code) {
            case 0 -> PENDING;
            case 1 -> COMPLETED;
            case 2 -> IN_PROGRESS;
            case 3 -> EXPIRED;
            default -> throw new IllegalArgumentException("올바르지 않은 code 입니다: " + code);
        };
    }
}