package me.totalfreedom.datura.backup;

import me.totalfreedom.api.backup.BackupSettings;
import me.totalfreedom.api.data.DataSettings;

abstract class BackupSettingsImpl implements BackupSettings
{
    private final DataSettings dataSettings;

    protected BackupSettingsImpl(final DataSettings dataSettings)
    {
        this.dataSettings = dataSettings;
    }

    @Override
    public DataSettings getDataSettings()
    {
        return this.dataSettings;
    }
}
