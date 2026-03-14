package dto.group;

import java.util.List;

public class GroupUpdateRequest {
    int groupId;
    String name;
    List<Integer> members;

    public GroupUpdateRequest(int groupId, String name, List<Integer> users) {
        this.groupId = groupId;
        this.name = name;
        this.members = users;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getMembers() {
        return members;
    }
}
