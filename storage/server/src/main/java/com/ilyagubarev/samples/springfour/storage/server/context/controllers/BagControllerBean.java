package com.ilyagubarev.samples.springfour.storage.server.context.controllers;

import com.ilyagubarev.samples.springfour.common.server.kernel.context.controllers.ControllerBean;
import com.ilyagubarev.samples.springfour.storage.server.repositories.Bag;
import com.ilyagubarev.samples.springfour.storage.server.repositories.BagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/bags")
public class BagControllerBean extends ControllerBean {

    private final BagRepository bags;

    public BagControllerBean(BagRepository bags) {
        this.bags = bags;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Bag get(@PathVariable("id") int id) {
        return bags.get(id);
    }
}
