package shop.mtcoding.projoctbodykey.attendChallenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttendChallengeJPARepository extends JpaRepository<AttendChallenge, Integer> {

    @Query("select a from AttendChallenge a where a.user.id = :userId and a.status is null")
    Optional<AttendChallenge> closingTime(@Param("userId") Integer userId);

    @Query("select a from AttendChallenge a where a.user.id = :userId and a.status is not null")
    List<AttendChallenge> status(@Param("userId") Integer userId);
}
