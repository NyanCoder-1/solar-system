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
public class moon extends body {
    
    public moon (body root) {
        // "Я подумал, что проще и наглябнее будет считать, что одна земная сутка будет равняться пол-секунде"
        
        // Луна вращается вокруг своей оси не вращается, а вокруг родителя вращается за 14.5 секунд (27 дней / 2)
        super(root, "moon.png", 0.5f, 1.5f, 13.5f, 0.0f, false);
    }
    
}
