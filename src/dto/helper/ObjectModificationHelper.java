package dto.helper;

import java.time.LocalDateTime;

public class ObjectModificationHelper {
    public static LocalDateTime updateModifiedAt() {
        return LocalDateTime.now();
    }
}
