package top.shauna.shaunaiscoming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import top.shauna.shaunaiscoming.bean.User;

import java.util.Date;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/11 21:27
 * @E-Mail z1023778132@icloud.com
 */
public interface UsersRepository extends JpaRepository<User,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into users " +
            "(mail, password, name, home, registdata, type) " +
            "values " +
            "(?1,?2,?3,?4,?5,?6)",nativeQuery = true)
    int addUser(String phonenum, String password, String name, String home, Date registdata,Integer type);

    User findByMail(String mail);

}
