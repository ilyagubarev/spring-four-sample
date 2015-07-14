package com.ilyagubarev.samples.springfour.storage.server.repositories.auto;

import com.ilyagubarev.samples.springfour.storage.server.repositories.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoItemRepository extends JpaRepository<Item, Integer> {

}
