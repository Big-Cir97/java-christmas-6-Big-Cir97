package christmas.model;

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
        String existMenu = "카레똥";
        return !existMenu.equals(name);
    }
}
