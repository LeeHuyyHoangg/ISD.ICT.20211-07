package org.hust.controller;

import org.hust.common.exception.InvalidLocationException;
import org.hust.common.exception.InvalidLocationFormatException;
import org.hust.entity.station.Station;
import org.hust.utils.Validator;

public class ViewStationController extends BaseController {

    public Station searchStationByLocation(String location)
            throws InvalidLocationException, InvalidLocationFormatException {

        if(validateLocation(location)){
            return (new Station()).getStationByLocation(location);
        } else {
            throw new InvalidLocationFormatException();
        }
    }

    public boolean validateId(String id) {
        return Validator.validateNoSpecialCharacterString(id);
    }

    public boolean validateLocation(String location) {
        return Validator.validateSomeSpecialCharacterString(location,',','.',' ');
    }

}
