package gradle_mybatis_spring_study.dto;

import java.util.Date;

public class Course {

	private int courseId;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private int tutorId;

	public Course() {
		super();
	}

	public Course(int courseId, String name, String description, Date starDate, Date endDate, int tutorId) {
		this.courseId = courseId;
		this.name = name;
		this.description = description;
		this.startDate = starDate;
		this.endDate = endDate;
		this.tutorId = tutorId;
	}

	public Course(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStarDate() {
		return startDate;
	}

	public void setStarDate(Date starDate) {
		this.startDate = starDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getTutorId() {
		return tutorId;
	}

	public void setTutorId(int tutorId) {
		this.tutorId = tutorId;
	}

	@Override
	public String toString() {
		return String.format("Course [%s, %s, %s, %s, %s, %s]", courseId, name, description, startDate, endDate, tutorId);
	}

}
