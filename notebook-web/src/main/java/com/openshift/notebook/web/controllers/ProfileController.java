package com.openshift.notebook.web.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openshift.notebook.core.domain.Profile;
import com.openshift.notebook.core.service.ProfileService;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

	@Inject
	private ProfileService profileService;

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public Profile createFromJson(@RequestBody String json) {
		Profile profile = Profile.fromJsonToProfile(json);
		profile = profileService.createProfile(profile);
		return profile;
	}

	@RequestMapping(value = "/{id}", headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public Profile showJson(@PathVariable("id") Long id) {
		Profile profile = profileService.findProfile(id);
		return profile;
	}

	@RequestMapping(headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public List<Profile> listJson() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		List<Profile> profiles = profileService.findAllProfiles();
		return profiles;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json", produces = "application/json")
	public Profile updateFromJson(@PathVariable("id") Long id,
			@RequestBody String json) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Profile profile = Profile.fromJsonToProfile(json);
		profile.setId(id);
		if (profileService.updateProfile(profile) == null) {
			return null;
		}
		return profile;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
		Profile profile = profileService.findProfile(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		if (profile == null) {
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		}
		profileService.deleteProfile(id);
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}
}
