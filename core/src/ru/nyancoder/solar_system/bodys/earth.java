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
public class earth extends body {
    
    public earth (body root) {
        // "Я подумал, что проще и наглябнее будет считать, что одна земная сутка будет равняться пол-секунде"
        
        // Земля вращается вокруг своей оси за пол-секунды (сутка / 2), а вокруг родителя за 182.5 (365 дней / 2)
        super(root, "bodys/earth.png", 0.2f, 2.5f, 182.5f, 0.5f);
    }
    
}
