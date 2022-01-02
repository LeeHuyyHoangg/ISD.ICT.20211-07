package org.hust.controller;

import lombok.SneakyThrows;
import org.hust.common.exception.HaveNotRentBikeException;
import org.hust.entity.bike.Bike;
import org.hust.views.popup.PopupScreen;

/**
 * @author hoang.lh194766
 * <p>
 * controller for view bike use-case
 */
public class ViewBikeController extends BaseController {

    private static ViewBikeController instance;

    public static ViewBikeController getInstance() {
        if (instance == null) {
            instance = new ViewBikeController();
        }
        return instance;
    }

    @SneakyThrows
    public Bike checkUserRentedBike() {
        Bike bike = RentBikeController.getCurrentlyRentedBike();
        if (bike == null) {
            PopupScreen.error(new HaveNotRentBikeException().getMessage());
            return null;
        } else {
            return bike;
        }
    }
}
