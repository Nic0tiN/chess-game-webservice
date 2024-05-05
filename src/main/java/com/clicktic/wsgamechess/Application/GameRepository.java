package com.clicktic.wsgamechess.Application;

import com.clicktic.wsgamechess.Domain.GameInstance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<GameInstance, String> {

}
