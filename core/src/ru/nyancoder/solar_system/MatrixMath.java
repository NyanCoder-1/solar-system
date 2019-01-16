/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nyancoder.solar_system;

import com.badlogic.gdx.math.Matrix4;

/**
 *
 * @author NyanCoder
 */
public class MatrixMath {
    
    public static Matrix4 MatRotate(float axisX, float axisY, float axisZ, float angle) {
        return (new Matrix4()).rotate(axisX, axisY, axisZ, angle);
    }
    public static Matrix4 MatTranslate(float x, float y, float z) {
        return (new Matrix4()).trn(x, y, z);
    }
    public static Matrix4 MatScale(float x, float y, float z) {
        return (new Matrix4()).scl(x, y, z);
    }
    public static Matrix4 MatScale(float scale) {
        return (new Matrix4()).scl(scale, scale, scale);
    }
    
}
