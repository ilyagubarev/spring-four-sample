package com.ilyagubarev.samples.springfour.storage.server.context.controllers;

import com.ilyagubarev.samples.springfour.common.server.kernel.context.controllers.ControllerBean;
import com.ilyagubarev.samples.springfour.storage.server.repositories.Item;
import com.ilyagubarev.samples.springfour.storage.server.repositories.auto.AutoItemRepository;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/items")
public class ItemControllerBean extends ControllerBean {

    private final AutoItemRepository items;

    public ItemControllerBean(AutoItemRepository items) {
        this.items = items;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Item get(@PathVariable("id") int id) {
        return items.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Collection<Item> filter() {
        return items.findAll();
    }
}
