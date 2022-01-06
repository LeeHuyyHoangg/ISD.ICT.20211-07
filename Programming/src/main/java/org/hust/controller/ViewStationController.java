package org.hust.controller;

import lombok.SneakyThrows;
import org.hust.common.exception.InvalidLocationFormatException;
import org.hust.entity.station.Station;
import org.hust.utils.Validator;
import org.hust.views.popup.PopupScreen;

import java.util.List;

/**
 * @author hoang.lh194766
 * <p>
 * the controller for view station use case
 */
public class ViewStationController extends BaseController {

    private static ViewStationController instance;

    public static ViewStationController getInstance() {
        if (instance == null) {
            instance = new ViewStationController();
        }
        return instance;
    }

    @SneakyThrows
    public List<Station> searchStationByLocation(String location) {

        if (validateLocation(location)) {
            return Station.getStationByLocation(location);
        } else {
            PopupScreen.error(new InvalidLocationFormatException().getMessage());
            return null;
        }
    }

    public List<Station> listStation() {
        return Station.listAllStation();
    }

    public boolean validateId(String id) {
        return Validator.validateNoSpecialCharacterString(id);
    }

    public boolean validateLocation(String location) {
        return Validator.validateSomeSpecialCharacterString(location, ',', '.', ' ');
    }
}
