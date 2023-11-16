package christmas;

import christmas.controller.ChristmasController;
import christmas.validation.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputValidator inputValidator = new InputValidator();
        InputView inputView = new InputView(inputValidator);
        OutputView outputView = new OutputView();

        ChristmasController controller = new ChristmasController(inputView, outputView);
        controller.visitRestaurant();
    }
}
