package org.hust.controller;

import lombok.SneakyThrows;
import org.hust.entity.Station;
import org.hust.exception.InvalidLocationFormatException;
import org.hust.utils.Validator;
import org.hust.views.popup.PopupScreen;

import java.util.List;

public class ViewStationController extends BaseController {

    private static ViewStationController instance;

    @SneakyThrows
    public List<Station> searchStationByLocation(String location) {

        if(validateLocation(location)){
            return Station.getStationByLocation(location);
        } else {
            PopupScreen.error(new InvalidLocationFormatException().getMessage());
            return null;
        }
    }

    public List<Station> listStation(){
        return Station.listAllStation();
    }

    public boolean validateId(String id) {
        return Validator.validateNoSpecialCharacterString(id);
    }

    public boolean validateLocation(String location) {
        return Validator.validateSomeSpecialCharacterString(location,',','.',' ');
    }

    public static ViewStationController getInstance() {
        if(instance == null){
            instance = new ViewStationController();
        }
        return instance;
    }
}
