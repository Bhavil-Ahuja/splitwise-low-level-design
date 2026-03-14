package repository;

import dto.group.Group;

import java.util.HashMap;
import java.util.Map;

public class GroupRepository {
    volatile static GroupRepository instance;
    Map<Integer, Group> groups = new HashMap<>();

    public static GroupRepository getInstance() {
        if (instance == null) {
            synchronized (GroupRepository.class) {
                if (instance == null) {
                instance = new GroupRepository();
                }
            }
        }
        return instance;
    }

    public Group findById(int id) {
        System.out.println("findById groupId=" + id);
        return groups.get(id);
    }

    public void save(Group group) {
        System.out.println("save groupId=" + group.getGroupId());
        groups.put(group.getGroupId(), group);
    }

    public void addGroup(Group group) {
        System.out.println("addGroup groupId=" + group.getGroupId());
        groups.put(group.getGroupId(), group);
    }

    public void removeGroupById(int groupId) {
        System.out.println("removeGroupById groupId=" + groupId);
        groups.remove(groupId);
    }
}
