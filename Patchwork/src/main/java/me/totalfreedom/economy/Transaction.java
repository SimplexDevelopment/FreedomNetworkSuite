package me.totalfreedom.economy;

/**
 * A class that denotes the transfer of currency between two EconomicEntity instances.
 */
public interface Transaction
{
    /**
     * @return the initiating entity
     */
    EconomicEntity getSource();

    /**
     * @return the destination entity
     */
    EconomicEntity getDestination();

    /**
     * @return the balance transferred in this Transaction
     */
    long getBalance();

}
