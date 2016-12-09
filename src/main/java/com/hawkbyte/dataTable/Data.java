/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.dataTable;
import com.hawkbyte.model.Activity;
import com.hawkbyte.model.Initiative;
import com.hawkbyte.model.Membership;
import com.hawkbyte.model.Project;
import com.hawkbyte.model.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Reknek
 */
public class Data {
    private List<Resource> resource;
    private List<Membership> membership;
    private Map<String,String> map;
    private List<String> list;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<Membership> getMembership() {
        return membership;
    }

    public void setMembership(List<Membership> membership) {
        this.membership = membership;
    }

    public List<Resource> getResource() {
        return resource;
    }

    public void setResource(List<Resource> resource) {
        this.resource = resource;
    }
   
    
}
