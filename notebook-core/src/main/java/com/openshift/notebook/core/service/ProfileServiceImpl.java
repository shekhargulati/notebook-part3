package com.openshift.notebook.core.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.openshift.notebook.core.domain.Profile;
import com.openshift.notebook.core.repository.jpa.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Inject
	ProfileRepository profileRepository;
	
	@Override
	public Profile createProfile(Profile profile) {
		return profileRepository.save(profile);
	}

	@Override
	public Profile findProfile(Long id) {
		return profileRepository.findOne(id);
	}

	@Override
	public Profile updateProfile(Profile updatedProfile) {
		return profileRepository.save(updatedProfile);
	}

	@Override
	public void deleteProfile(Long id) {
		profileRepository.delete(id);
	}

	@Override
	public List<Profile> findAllProfiles() {
		return profileRepository.findAll();
	}

}
