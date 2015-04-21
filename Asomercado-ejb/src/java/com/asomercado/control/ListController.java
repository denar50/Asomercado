/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.control;

import com.asomercado.dao.ListDAO;
import com.asomercado.dao.ListItemDAO;
import com.asomercado.dao.UnitDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author USUARIO1
 */
@Stateless
@LocalBean
public class ListController {
    @EJB
    private UnitDAO unitDAO;
    @EJB
    private ListItemDAO listItemDAO;
    @EJB
    private ListDAO listDAO;
    
    

}
