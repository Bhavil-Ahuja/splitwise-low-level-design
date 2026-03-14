package dto.group;

import dto.helper.ObjectModificationHelper;

import java.time.LocalDateTime;
import java.util.List;

public class Group {
    private final int groupId;
    String name;
    List<Integer> members;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Group(int groupId, String name, List<Integer> members) {
        this.groupId = groupId;
        this.name = name;
        this.members = members;
        this.createdAt = this.modifiedAt = LocalDateTime.now();
    }

    public int getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }
}
