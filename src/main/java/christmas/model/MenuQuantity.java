package christmas.model;

import static christmas.utils.Constants.MAX_ORDER_QUANTITY;
import static christmas.utils.Constants.MIN_ORDER_QUANTITY;

public class MenuQuantity {

    private final int quantity;

    public MenuQuantity(int quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    private void validate(int quantity) {
        validateQuantityRange(quantity);
    }

    private void validateQuantityRange(int quantity) {
        if (checkMinQuantity(quantity)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        if (checkMaxQuantity(quantity)) {
            throw new IllegalArgumentException(String.format("[ERROR] 주문 수량은 최대 %d개 입니다. 다시 입력해 주세요.",
                    MIN_ORDER_QUANTITY));
        }
    }

    private boolean checkMinQuantity(int quantity) {
        return quantity < MIN_ORDER_QUANTITY;
    }

    private boolean checkMaxQuantity(int quantity) {
        return quantity > MAX_ORDER_QUANTITY;
    }
}
