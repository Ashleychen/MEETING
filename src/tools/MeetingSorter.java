package tools;

import java.util.Comparator;

import data.MeetingInfo;

public class MeetingSorter implements Comparator<MeetingInfo>{
	public int compare(MeetingInfo meeting1, MeetingInfo meeting2) {
		return meeting1.getStartTime().compareTo(meeting2.getStartTime());
	}
}
