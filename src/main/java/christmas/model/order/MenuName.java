package christmas.model.order;

import static christmas.exception.ErrorType.INVALID_MENU_NAME;

import christmas.exception.ErrorType;
import christmas.model.order.enums.MenuInfo;
import java.util.Objects;

public class MenuName {
    private final String name;

    public MenuName(String name) {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validate(String name) {
        validateMenuName(name);
    }

    private void validateMenuName(String name) {
        if (hasMenu(name)) {
            throw new IllegalArgumentException(INVALID_MENU_NAME.getMessage());
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
        MenuName menuName = (MenuName) o;
        return Objects.equals(name, menuName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
