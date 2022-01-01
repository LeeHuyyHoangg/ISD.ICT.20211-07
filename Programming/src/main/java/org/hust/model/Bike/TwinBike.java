package org.hust.model.Bike;

import org.bson.Document;
import org.hust.utils.Utils;

public class TwinBike extends Bike{
    @Override
    protected TwinBike documentToBike(Document document) {
        return Utils.documentToObject(document,TwinBike.class);
    }
}
