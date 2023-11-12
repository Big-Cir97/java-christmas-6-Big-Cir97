package christmas.model;

public class MeunQuantity {

    private static final int MIN_ORDER_COUNT = 1;
    private static final int MAX_ORDER_COUNT = 20;

    private final int quantity;

    public MeunQuantity(int quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    private void validate(int count) {
        validateRange(count);
    }

    private void validateRange(int count) {
        if (checkMinCount(count)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        if (checkMaxCount(count)) {
            throw new IllegalArgumentException(String.format("[ERROR] 주문 수량은 최대 %d개 입니다. 다시 입력해 주세요.", MAX_ORDER_COUNT));
        }
    }

    private boolean checkMinCount(int count) {
        return count < MIN_ORDER_COUNT;
    }

    private boolean checkMaxCount(int count) {
        return count > MAX_ORDER_COUNT;
    }
}
