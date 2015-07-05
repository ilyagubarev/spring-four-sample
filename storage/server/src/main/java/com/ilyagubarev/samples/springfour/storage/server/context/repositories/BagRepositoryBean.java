package com.ilyagubarev.samples.springfour.storage.server.context.repositories;

import java.util.HashMap;
import java.util.Map;

import com.ilyagubarev.samples.springfour.common.server.kernel.context.repositories.DatabaseRepositoryBean;
import com.ilyagubarev.samples.springfour.storage.server.repositories.Bag;
import com.ilyagubarev.samples.springfour.storage.server.repositories.BagRepository;

public class BagRepositoryBean extends DatabaseRepositoryBean
        implements BagRepository {

    @Override
    public Map<Integer, Bag> filter() {
        return new HashMap<>();
    }

    @Override
    public Bag get(int id) {
        return new Bag();
    }
}
