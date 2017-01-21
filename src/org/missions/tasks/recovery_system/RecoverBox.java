package org.missions.tasks.recovery_system;

import org.missions.OrionRuneMys;
import org.missions.data.RuneMys_Vars;
import org.missions.data.enums.RM_QuestNPC;
import org.missions.data.enums.RM_QuestObject;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/12/2017.
 */
public class RecoverBox extends Task<OrionRuneMys> {

    private NPC sedridor;

    public RecoverBox(OrionRuneMys mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(63) == 3 && !inventory.contains(RM_QuestObject.BOX.getItemID());
    }

    @Override
    public void execute() {
        if (!RuneMys_Vars.get().has_checked_bank_for_box) {
            if (bank.isOpen()) {
                final Item item_to_withdraw = bank.getItem(RM_QuestObject.BOX.getItemID());
                if (item_to_withdraw != null) {
                    final Item[] inventory_cache = inventory.getItems();
                    if (bank.withdraw(item_to_withdraw.getId(), 1))
                        if (Timing.waitCondition(() -> inventory_cache.length != inventory.getItems().length, 150, random(2000, 2500)))
                            RuneMys_Vars.get().has_checked_bank_for_box = true;
                } else {
                    RuneMys_Vars.get().has_checked_bank_for_box = true;
                }
            } else {
                if (bankUtils.isInBank()) {
                    if (bankUtils.open())
                        Timing.waitCondition(conditions.BANK_OPEN, 150, random(2000, 2500));
                } else {
                    if (getWalking().webWalk(bankUtils.getAllBanks(false, false)))
                        Timing.waitCondition(() -> bankUtils.isInBank(), 150, random(2000, 2500));
                }
            }
        } else {
            sedridor = npcs.closest(RM_QuestNPC.SEDRIDOR.getNPCArea(), RM_QuestNPC.SEDRIDOR.getNPCName());
            if (sedridor != null && map.canReach(sedridor)) {
                iFact.dialogue("Talk-to", RM_QuestNPC.SEDRIDOR.getNPCName(), 20, 1).execute();
            } else {
                if (walkUtils.walkToArea(RM_QuestNPC.SEDRIDOR.getNPCArea(), () -> {
                    sedridor = npcs.closest(RM_QuestNPC.SEDRIDOR.getNPCArea(), RM_QuestNPC.SEDRIDOR.getNPCName());
                    return sedridor != null && map.canReach(sedridor);
                })) {
                    Timing.waitCondition(() -> npcs.closest(RM_QuestNPC.SEDRIDOR.getNPCArea(), RM_QuestNPC.SEDRIDOR.getNPCName()) != null, 150, random(2000, 2500));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Recovering box";
    }

}

