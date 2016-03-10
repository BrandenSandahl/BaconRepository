package com.sixtelmedia.Services;

import com.sixtelmedia.Entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by branden on 3/10/16 at 13:31.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
