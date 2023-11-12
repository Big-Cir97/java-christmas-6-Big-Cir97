package christmas.model;

import christmas.enums.MenuInfo;
import java.util.Objects;

public class MenuName {
    private String name;

    public MenuName(String name) {
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
        MenuName menuName = (MenuName) o;
        return Objects.equals(name, menuName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
