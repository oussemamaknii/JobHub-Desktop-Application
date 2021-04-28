/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Services.evenementService;

import java.sql.SQLException;

public class AutoComplete {

    public static  String[] MotsAutoComplete() throws SQLException {

        evenementService sp = new evenementService();

        String[] data = sp.getData();

        return data;
    };
}
