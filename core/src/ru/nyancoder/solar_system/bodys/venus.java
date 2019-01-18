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
public class venus extends body {
    
    public venus (body root) {
        // "Я подумал, что проще и наглябнее будет считать, что одна земная сутка будет равняться пол-секунде"
        
        // Венера вращается вокруг родителя за 112.5 секунд (225 дней / 2), а вокруг своей оси за -121.5 (-243 дней / 2)
        super(root, "bodys/venus.png", 0.18f, 1.5f, 112.5f, -121.5f);
    }
    
}
