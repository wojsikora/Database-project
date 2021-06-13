package com.dmodels.app.orders.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@RequiredArgsConstructor
public class Vector3D {
    double width;
    double depth;
    double height;

    public boolean lowerThan(Vector3D second){
        return this.width < second.width && this.depth < second.depth && this.height < second.height;
    }

    public Double[] toArray(){
        return new Double[] {this.width, this.depth, this.height};
    }

    @Override
    public String toString() {
        return "Vector3D{" +
                "width=" + width +
                ", depth=" + depth +
                ", height=" + height +
                '}';
    }
}
