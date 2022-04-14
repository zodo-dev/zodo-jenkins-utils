package dev.zodo.devops.jenkins.utils.kubernetes;

import dev.zodo.devops.jenkins.utils.kubernetes.annotations.FieldProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Data(staticConstructor = "of")
@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PRIVATE)
public class EnvVars implements BuildString {

    @FieldProperty
    List<EnvVar> containerEnvVar;

    @FieldProperty
    List<EnvVar> envVar;

    @FieldProperty
    List<EnvVar> podEnvVar;

    @FieldProperty
    List<EnvVar> secretEnvVar;

    public EnvVars containerEnvVar(EnvVar envVar) {
        return initVarCollection(this::getContainerEnvVar, this::setContainerEnvVar, envVar);
    }

    public EnvVars envVar(EnvVar envVar) {
        return initVarCollection(this::getEnvVar, this::setEnvVar, envVar);
    }

    public EnvVars podEnvVar(EnvVar envVar) {
        return initVarCollection(this::getPodEnvVar, this::setPodEnvVar, envVar);
    }

    private EnvVars initVarCollection(Supplier<List<EnvVar>> getter, Consumer<List<EnvVar>> setter, EnvVar envVar) {
        List<EnvVar> col = getter.get();
        if (col == null) {
            col = new ArrayList<>();
            setter.accept(col);
        }
        col.add(envVar);
        return this;
    }

    @Override
    public String templateFormat(boolean wrap) {
        String wrapChar = wrap ? wrapChar() : "";
        return String.format("[%s%%s%s]", wrapChar, wrapChar);
    }

    @Override
    public String buildString(boolean wrap) {
        String wrapChar = wrap ? wrapChar() : "";
        String tplFormat = templateFormat(wrap);
        int indentSize = wrap && this.allowWrap() ? this.indentSize() : 0;
        String str = Utils.indent(getFieldProperty().stream()
                .flatMap(kvd -> {
                    List<EnvVar> keyValues = ((List<EnvVar>) kvd.getValue());
                    return keyValues.stream()
                            .map(kv -> String.format("%s(%s)", kvd.getKey(), kv.buildString(wrap)))
                            .collect(Collectors.toList()).stream();
                })
                .collect(Collectors.joining(String.format(",%s", wrapChar))), indentSize);
        return tplFormat == null ? str : String.format(tplFormat, str);
    }
}
