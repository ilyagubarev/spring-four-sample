package com.ilyagubarev.samples.springfour.storage.server.repositories.auto;

import com.ilyagubarev.samples.springfour.storage.server.repositories.Bag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoBagRepository extends JpaRepository<Bag, Integer> {

}
