package com.ilyagubarev.samples.springfour.storage.server.repositories;

import java.util.Map;

public interface BagRepository {

    Map<Integer, Bag> filter();

    Bag get(int id);
}
