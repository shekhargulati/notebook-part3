package com.openshift.notebook.core.service;

import java.util.List;

import com.openshift.notebook.core.domain.Profile;

public interface ProfileService {
	
	public Profile createProfile(Profile profile);
	
	public Profile findProfile(Long id);
	
	public Profile updateProfile(Profile updatedProfile);
	
	public void deleteProfile(Long id);

	public List<Profile> findAllProfiles();

}
