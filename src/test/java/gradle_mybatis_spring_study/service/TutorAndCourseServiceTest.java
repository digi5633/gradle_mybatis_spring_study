package gradle_mybatis_spring_study.service;

import java.util.Date;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gradle_mybatis_spring_study.config.ContextRoot;
import gradle_mybatis_spring_study.dto.Address;
import gradle_mybatis_spring_study.dto.Course;
import gradle_mybatis_spring_study.dto.PhoneNumber;
import gradle_mybatis_spring_study.dto.Tutor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TutorAndCourseServiceTest {

	protected static final Log log = LogFactory.getLog(TutorAndCourseServiceTest.class);

	@Autowired
	private TutorAndCourseService service;

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test(expected = DuplicateKeyException.class)
	public void test01TrJoinTutorAndCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		Address address = new Address();
		address.setAddrId(2);
		PhoneNumber phone = new PhoneNumber("010-2222-2222");

		// 1번 교수는 존재
		Tutor tutor = new Tutor(1, "mskim", "net94@naver.com", phone, address);
		Course course = new Course(8, "Python", "Programming", new Date(), new Date(), 4);
		service.trJoinTutorAndCourse(tutor, course);
	}

	@Test(expected = DuplicateKeyException.class)
	public void test02TrJoinTutorAndCourse_FailCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		Address address = new Address();
		address.setAddrId(2);
		PhoneNumber phone = new PhoneNumber("010-2222-2222");

		// 2번 과목 존재, 해당 교수번호를 가진 교수가 존재하지않음
		Tutor tutor = new Tutor(5, "mskim", "net94@naver.com", phone, address);
		Course course = new Course(2, "Python", "Programming", new Date(), new Date(), 100);
		service.trJoinTutorAndCourse(tutor, course);
	}

	@Test
	public void test03TrJoinTutorAndCourse_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		Address address = new Address();
		address.setAddrId(2);
		PhoneNumber phone = new PhoneNumber("010-2222-2222");

		Tutor tutor = new Tutor(5, "mskim", "net94@naver.com", phone, address);
		Course course = new Course(8, "Python", "Programming", new Date(), new Date(), 5);
		service.trJoinTutorAndCourse(tutor, course);
	}

	@Test(expected = RuntimeException.class)
	public void test04TrRemoveTutorAndCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		service.trRemoveTutorAndCourse(10, 8); // 10번교수미존재, 8번과목존재

	}

	@Test(expected = RuntimeException.class)
	public void test05TrRemoveTutorAndCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		service.trRemoveTutorAndCourse(5, 10); // 5번교수존재, 10번과목없음

	}

	@Test
	public void test06TrRemoveTutorAndCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		service.trRemoveTutorAndCourse(5, 8);

	}

}
