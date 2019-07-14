
package com.andalus.mvpwithstrategy.entities;

import com.google.gson.annotations.Expose;

public class Wind {

    @Expose
    private Long deg;
    @Expose
    private Double speed;

    public Long getDeg() {
        return deg;
    }

    public void setDeg(Long deg) {
        this.deg = deg;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

}
