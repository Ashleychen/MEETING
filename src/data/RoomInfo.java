package data;

public class RoomInfo {
	private String roomName;
	private int personNum;
	private String roomSize;
	private String position;
	
	public RoomInfo(String rn, int pn, String rs, String pos) {
		// TODO Auto-generated constructor stub
		roomName = rn;
		personNum = pn;
		roomSize = rs;
		pos = position;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public int getPersonNum() {
		return personNum;
	}
	
	public String getRoomSize() {
		return roomSize;
	}
	
	public String getPosition() {
		return position;
	}
}
