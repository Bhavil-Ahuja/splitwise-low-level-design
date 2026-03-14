package manager;

import dto.group.Group;
import dto.group.GroupUpdateRequest;
import repository.GroupRepository;

public class GroupManager {
    GroupRepository groupRepository;

    public GroupManager() {
        groupRepository = GroupRepository.getInstance();
    }

    public void createGroup(Group group) {
        System.out.println("createGroup groupId=" + group.getGroupId());
        groupRepository.addGroup(group);
    }

    public void deleteGroup(int groupId) {
        System.out.println("deleteGroup groupId=" + groupId);
        groupRepository.removeGroupById(groupId);
    }

    public void editGroup(GroupUpdateRequest groupUpdateRequest) {
        System.out.println("editGroup groupId=" + groupUpdateRequest.getGroupId());
        Group group = groupRepository.findById(groupUpdateRequest.getGroupId());
        if (groupUpdateRequest.getName() != null) {
            group.setName(groupUpdateRequest.getName());
        }
        if (groupUpdateRequest.getMembers() != null) {
            group.setMembers(groupUpdateRequest.getMembers());
        }
        groupRepository.save(group);
    }
}
