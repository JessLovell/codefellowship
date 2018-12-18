package com.jessica.codefellowship.applicationUsers;

import com.jessica.codefellowship.applicationUsers.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
}
