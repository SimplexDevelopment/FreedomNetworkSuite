package me.totalfreedom.economy;

/**
 * An entity that is able to transfer sums of currency between other {@link EconomicEntity}
 */
public interface EconomicEntity
{
    /**
     * Gets the {@link EconomicEntityData} (which contains various common metadata about this {@link EconomicEntity})
     * associated with this class
     *
     * @return the {@link EconomicEntityData}
     */
    EconomicEntityData getEconomicData();

    /**
     * The name of the economic entity
     *
     * @return the economic entity's name
     */
    String getName();
}
