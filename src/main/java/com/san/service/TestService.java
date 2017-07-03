package com.san.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.utils.UUIDs;
import com.san.db.PaginatedData;
import com.san.domain.SampleData;
import com.san.domain.User;
import com.san.repo.SampleDataRepository;
import com.san.repo.UserRepository;

@Component
public class TestService {

	@Autowired
	UserService userService;

	@Autowired
	DBCommonService dbCommonService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	SampleDataRepository sampleDataRepository;

	public void testApp() {
		try {
			System.out.println("Record count : " + userRepository.count());
			PaginatedData<User> paginatedData = userRepository.selectBatch(null);
			System.out.println("Batch Data : " + paginatedData);
			UUID uuid = UUIDs.timeBased();
			uuid = insertUsers();
			System.out.println("Record inserted with UUID : " + uuid);
			System.out.println("Fetch record : " + fetchUser(uuid));
			System.out.println("Record count : " + dbCommonService.countRecords(User.class));
			SampleData data = insertSampledata();
			System.out.println("Fetched sample data : " + sampleDataRepository.findOne(data.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private UUID insertUsers() {
		int _x = 6;
		UUID uuid = null;
		for (int y = 0; y < 10; y++) {
			User user = new User("xyz " + y + " name " + _x, y + "xyz" + _x + "@san.com", y + "xyz" + _x);
			user.setPassword("password");
			user = userRepository.save(user);
			uuid = user.getId();
		}
		return uuid;
	}

	private User fetchUser(UUID id) {
		User usr = userRepository.findByID(id).get(0);
		MapId mapId = new BasicMapId();
		mapId.with("id", usr.getId());
		mapId.with("createdDateTime", usr.getCreatedDateTime());
		return userRepository.findOne(mapId);
	}

	private SampleData insertSampledata() {
		SampleData data = null;
		for (int x = 0; x < 10; x++) {
			data = new SampleData();
			data.setName("name : " + x);
			data = sampleDataRepository.save(data);
		}
		return data;
	}
}
