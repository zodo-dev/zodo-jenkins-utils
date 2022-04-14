package dev.zodo.devops.jenkins.utils.kubernetes.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "")
@Data(staticConstructor = "of")
public class KeyValueData {
    final String key;
    final Object value;
}

