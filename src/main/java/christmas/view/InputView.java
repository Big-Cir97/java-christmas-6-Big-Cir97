package christmas.view;

import static christmas.view.enums.InputMessage.INPUT_ORDER_MENU_MESSAGE;
import static christmas.view.enums.InputMessage.INPUT_START_MESSAGE;
import static christmas.view.enums.InputMessage.INPUT_VISIT_DAY_MESSAGE;

import camp.nextstep.edu.missionutils.Console;
import christmas.utils.Converter;
import christmas.validation.InputValidator;
import java.util.Map;

public class InputView {

    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public int inputVistiDay() {
        System.out.println(INPUT_START_MESSAGE.getMessage());
        System.out.println(INPUT_VISIT_DAY_MESSAGE.getMessage());

        String input = Console.readLine();
        inputValidator.validateVisitDay(input);
        return Converter.toInteger(input);
    }

    public Map<String, Integer> inputOrderMenu() {
        System.out.println(INPUT_ORDER_MENU_MESSAGE.getMessage());

        String input = Console.readLine();
        return inputValidator.validateOrderMenu(input);
    }
}
