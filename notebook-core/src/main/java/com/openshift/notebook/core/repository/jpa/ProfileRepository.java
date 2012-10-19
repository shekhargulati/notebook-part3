package com.openshift.notebook.core.repository.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openshift.notebook.core.domain.Profile;

@Repository
public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {

	List<Profile> findAll();
}
