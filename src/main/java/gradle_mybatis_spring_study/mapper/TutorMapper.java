package gradle_mybatis_spring_study.mapper;

import gradle_mybatis_spring_study.dto.Tutor;

public interface TutorMapper {

	Tutor selectTutorByTutorId(Tutor tutor);

	// Transaction
	int insertTutor(Tutor tutor);

	int deleteTutor(int tutorId);

}
