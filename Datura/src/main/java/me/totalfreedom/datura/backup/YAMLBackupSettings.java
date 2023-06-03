package me.totalfreedom.datura.backup;

import me.totalfreedom.api.data.DataSettings;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.util.function.Function;

public class YAMLBackupSettings extends BackupSettingsImpl
{
    static
    {
        final DumperOptions dumperOptions = new DumperOptions();

        dumperOptions.setPrettyFlow(true);
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        YAML = new Yaml(dumperOptions);
    }

    private static final Yaml YAML;

    public YAMLBackupSettings(final DataSettings dataSettings)
    {
        super(dataSettings);
    }

    @Override
    public Function<Object, String> getSerializationFunction()
    {
        return YAML::dump;
    }

    @Override
    public Function<String, Object> getDeserializationFunction()
    {
        return YAML::load;
    }

    @Override
    public String getFileExtension()
    {
        return ".yml";
    }
}
