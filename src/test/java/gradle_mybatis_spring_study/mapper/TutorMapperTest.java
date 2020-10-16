package gradle_mybatis_spring_study.mapper;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TutorMapperTest {

	protected static final Log log = LogFactory.getLog(TutorMapperTest.class);

	@Autowired
	private TutorMapper mapper;

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectTutorByTutorId() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Tutor findTutor = new Tutor();
		findTutor.setTutorId(1);
		Tutor tutor = mapper.selectTutorByTutorId(findTutor);
		Assert.assertEquals(tutor.getTutorId(), findTutor.getTutorId());

		log.trace(tutor.getTutorId() + " : " + tutor.getName());
		List<Course> list = tutor.getCourses();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test02InsertTutorAndDeleteTutor() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Address address = new Address();
		address.setAddrId(2);
		
		PhoneNumber phone = new PhoneNumber("010-2222-2222");
		Tutor tutor = new Tutor(5, "mskim", "net94@naver.com", phone, address);
		
		int res = mapper.insertTutor(tutor);
		Tutor findTutor = mapper.selectTutorByTutorId(tutor);
		log.debug(findTutor.toString());
		res += mapper.deleteTutor(tutor.getTutorId());
		Assert.assertEquals(2, res);
	}

}
