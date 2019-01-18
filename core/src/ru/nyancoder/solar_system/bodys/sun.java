/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nyancoder.solar_system.bodys;

import ru.nyancoder.solar_system.bodys.body;

/**
 *
 * @author NyanCoder
 */
public class sun extends body {
    
    public sun () {
        // "Я подумал, что проще и наглябнее будет считать, что одна земная сутка будет равняться пол-секунде"
        
        // Солнце не вращается ни вокруг своей оси, ни вокруг чего-либо
        super(null, "bodys/sun.png", 0.2f, 0.0f, 0.0f, 0.0f);
    }
    
}
