package org.missions.tasks;

import org.missions.OrionRuneMys;
import org.missions.data.RuneMys_Vars;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/12/2017.
 */
public class RM_DepositItems extends Task<OrionRuneMys> {

    public RM_DepositItems(OrionRuneMys mission) {
        super(mission);
    }

    public boolean validate() {
        if (inventory.isEmpty())
            RuneMys_Vars.get().has_emptied_inventory = true;

        return !inventory.isEmpty() && !RuneMys_Vars.get().has_emptied_inventory;
    }

    public void execute() {
        if (bank.isOpen()) {
            if (bank.depositAll())
                if (Timing.waitCondition(() -> !inventory.isFull(), 150, random(2000, 2500)))
                    RuneMys_Vars.get().has_emptied_inventory = true;
        } else {
            if (bankUtils.isInBank()) {
                if (bankUtils.open())
                    Timing.waitCondition(conditions.BANK_OPEN, 150, random(2000, 2500));
            } else {
                if (getWalking().webWalk(bankUtils.getAllBanks(false, false)))
                    Timing.waitCondition(() -> bankUtils.isInBank(), 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Depositing items";
    }

}

