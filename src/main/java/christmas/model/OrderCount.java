package christmas.model;

public class OrderCount {

    private static final int MIN_ORDER_COUNT = 1;
    private static final int MAX_ORDER_COUNT = 20;

    private int count;

    public OrderCount(int count) {
        validate(count);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    private void validate(int count) {
        validateRange(count);
    }

    private void validateRange(int count) {
        if (checkMinCount(count)) {
            throw new IllegalArgumentException(String.format("주문 수량은 최소 %d개 입니다.", MIN_ORDER_COUNT));
        }

        if (checkMaxCount(count)) {
            throw new IllegalArgumentException(String.format("주문 수량은 최대 %d개 입니다.", MAX_ORDER_COUNT));
        }
    }

    private boolean checkMinCount(int count) {
        return count < MIN_ORDER_COUNT;
    }

    private boolean checkMaxCount(int count) {
        return count > MAX_ORDER_COUNT;
    }
}
