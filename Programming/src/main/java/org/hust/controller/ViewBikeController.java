package org.hust.controller;

import lombok.SneakyThrows;
import org.hust.entity.bike.Bike;
import org.hust.exception.HaveNotRentBikeException;
import org.hust.views.popup.PopupScreen;

public class ViewBikeController extends BaseController{

    private static ViewBikeController instance;

    @SneakyThrows
    public Bike checkUserRentedBike(){
        Bike bike = RentBikeController.getCurrentlyRentedBike();
        if(bike == null){
            PopupScreen.error(new HaveNotRentBikeException().getMessage());
            return null;
        } else {
            return bike;
        }
    }

    public static ViewBikeController getInstance() {
        if(instance == null){
            instance = new ViewBikeController();
        }
        return instance;
    }
}
