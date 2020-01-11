package com.odia.alphabet.realm;

import java.util.List;

/**
 * Created by Awesome PC on 02-May-18.
 */
public class OdiaAlphabetParcel {

    private String screenSize;
    private int textSize;
    private List<OdiaAlphabetCoordinate.CoordinatesBean> coordinatesBeanList;

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public List<OdiaAlphabetCoordinate.CoordinatesBean> getCoordinatesBeanList() {
        return coordinatesBeanList;
    }

    public void setCoordinatesBeanList(List<OdiaAlphabetCoordinate.CoordinatesBean>  coordinatesBeanList) {
        this.coordinatesBeanList = coordinatesBeanList;
    }

}
