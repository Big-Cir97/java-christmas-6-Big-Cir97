package christmas.model;

import christmas.enums.MenuInfo;
import java.util.Objects;

public class OrderMenu {
    private String name;

    public OrderMenu(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateMenuName(name);
    }

    private void validateMenuName(String name) {
        if (hasMenu(name)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean hasMenu(String name) {
        return !MenuInfo.isExistMenu(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderMenu orderMenu = (OrderMenu) o;
        return Objects.equals(name, orderMenu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
