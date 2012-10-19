package com.openshift.notebook.core.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.openshift.notebook.core.domain.Profile;
import com.openshift.notebook.core.domain.ProfileBuilder;
import com.openshift.notebook.core.repository.jpa.ProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional
@ActiveProfiles("in-memory")
public class ProfileRepositoryTest {

	@Inject
	ProfileRepository profileRepository;

	@Test
	public void shouldSaveProfile() {
		Profile profile = ProfileBuilder.profile().withActive(true)
				.withEmail("test@test.com").withPassword("password").build();
		profileRepository.save(profile);
		assertEquals(1, profileRepository.count());
	}

	@Test
	public void testFindOne() {
		Profile profile = ProfileBuilder.profile().withActive(true)
				.withEmail("test@test.com").withPassword("password").build();
		profileRepository.save(profile);
		
		Profile persistedProfile = profileRepository.findOne(profile.getId());
		
		assertEquals(profile, persistedProfile);
		
	}

	@Test
	public void testFindAll() {
		for(int i = 0;i<10;i++){
			Profile profile = ProfileBuilder.profile().withActive(true)
					.withEmail("test@test.com").withPassword("password").build();
			profileRepository.save(profile);
		}
		
		List<Profile> allProfiles = profileRepository.findAll();
		assertEquals(10, allProfiles.size());
	}

	@Test
	public void testDeleteID() {
		Profile profile = ProfileBuilder.profile().withActive(true)
				.withEmail("test@test.com").withPassword("password").build();
		profileRepository.save(profile);
		assertEquals(1, profileRepository.count());
		
		profileRepository.delete(profile.getId());
		assertEquals(0, profileRepository.count());
	}

}
