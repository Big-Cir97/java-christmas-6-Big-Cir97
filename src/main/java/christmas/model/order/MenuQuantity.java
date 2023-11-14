package christmas.model.order;

import static christmas.exception.ErrorType.INVALID_MAX_MENU_QUANTITY;
import static christmas.exception.ErrorType.INVALID_MENU_QUANTITY_UNDER_ONE;
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
            throw new IllegalArgumentException(INVALID_MENU_QUANTITY_UNDER_ONE.getMessage());
        }

        if (checkMaxQuantity(quantity)) {
            throw new IllegalArgumentException(INVALID_MAX_MENU_QUANTITY.getMessage());
        }
    }

    private boolean checkMinQuantity(int quantity) {
        return quantity < MIN_ORDER_QUANTITY;
    }

    private boolean checkMaxQuantity(int quantity) {
        return quantity > MAX_ORDER_QUANTITY;
    }
}
