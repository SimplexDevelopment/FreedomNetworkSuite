package me.totalfreedom.datura.backup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.totalfreedom.api.data.DataSettings;

import java.util.function.Function;

public class JSONBackupSettings extends BackupSettingsImpl
{
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public JSONBackupSettings(DataSettings dataSettings)
    {
        super(dataSettings);
    }

    @Override
    public Function<Object, String> getSerializationFunction()
    {
        return GSON::toJson;
    }

    @Override
    public Function<String, Object> getDeserializationFunction()
    {
        return o -> GSON.fromJson(o, o.getClass());
    }
    
    @Override
    public String getFileExtension()
    {
        return ".json";
    }
}
