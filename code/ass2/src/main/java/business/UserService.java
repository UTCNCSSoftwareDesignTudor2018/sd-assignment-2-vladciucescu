package business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataAccess.entity.User;
import dataAccess.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public void updateAddress(User user, String address) {
		user.setAddress(address);
		userRepo.save(user);
	}
	
	public void updateEmail(User user, String email) {
		user.setEmail(email);
		userRepo.save(user);
	}
}
