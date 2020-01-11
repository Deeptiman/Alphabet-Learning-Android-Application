package com.odia.alphabet.realm;

import java.util.List;

/**
 * Created by Awesome PC on 02-May-18.
 */
public class OdiaAlphabetCoordinate {

    private List<CoordinatesBean> coordinates;

    public List<CoordinatesBean> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<CoordinatesBean> coordinates) {
        this.coordinates = coordinates;
    }

    public static class CoordinatesBean {
        /**
         * circularShape : 0
         * id : 0
         * jid : 0
         * midX : 0
         * midY : 0
         * status : 0
         * submidX : 0
         * submidY : 0
         * x : 539
         * y : 535
         */

        private int circularShape;
        private int id;
        private int jid;
        private int midX;
        private int midY;
        private int status;
        private int submidX;
        private int submidY;
        private int x;
        private int y;

        public int getCircularShape() {
            return circularShape;
        }

        public void setCircularShape(int circularShape) {
            this.circularShape = circularShape;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getJid() {
            return jid;
        }

        public void setJid(int jid) {
            this.jid = jid;
        }

        public int getMidX() {
            return midX;
        }

        public void setMidX(int midX) {
            this.midX = midX;
        }

        public int getMidY() {
            return midY;
        }

        public void setMidY(int midY) {
            this.midY = midY;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSubmidX() {
            return submidX;
        }

        public void setSubmidX(int submidX) {
            this.submidX = submidX;
        }

        public int getSubmidY() {
            return submidY;
        }

        public void setSubmidY(int submidY) {
            this.submidY = submidY;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
