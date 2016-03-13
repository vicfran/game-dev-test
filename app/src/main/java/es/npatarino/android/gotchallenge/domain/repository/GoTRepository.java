package es.npatarino.android.gotchallenge.domain.repository;

import java.util.List;

import es.npatarino.android.gotchallenge.domain.GoTCharacter;

/**
 * GoTRepository is an interface for getting GoT data
 */
public interface GoTRepository {

    List<GoTCharacter> characters();

}
