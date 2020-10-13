package gradle_mybatis_spring_study.dto;

public class UserPic {

	private int id;
	private String name;
	private byte[] pic;
	private String bio;

	public UserPic() {
		super();
	}

	public UserPic(int id, String name, byte[] pic, String bio) {
		this.id = id;
		this.name = name;
		this.pic = pic;
		this.bio = bio;
	}

	public UserPic(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public String toString() {
		return String.format("UserPic[id=%s, name=%s, pic=%s, bio=%s]", id, name, pic.length, bio);
	}

}
